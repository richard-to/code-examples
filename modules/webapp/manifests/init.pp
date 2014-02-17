class webapp(
  $api_server_dir = '/vagrant/api_server',
  $site_dir = '/vagrant/site',
  $server_name = '192.168.245.5'
  ) {

  # Main Variables
  $module_uri = 'puppet:///modules/webapp'
  $serverowner = 'www-data'
  $servergroup = 'www-data'


  # Install pip
  class { 'java': }

  # Install ningx
  class { 'nginx': }

  # Install pip
  class { 'python_pip': }

  python_pip::install { 'install_fabric':
    name => 'fabric',
  }

  python_pip::install { 'install_uwsgi':
    name => 'uwsgi',
  }

  python_pip::install { 'install_flask':
    name => 'flask',
  }

  python_pip::install { 'install_requests':
    name => 'requests',
  }

  # Create log directory for uwsgi process
  file { "/var/log/uwsgi":
    ensure => "directory",
    owner  => $serverowner,
    group  => $servergroup,
  }

  # Create directory hold uwsgi configs
  file { ["/etc/uwsgi", "/etc/uwsgi/vassals"]:
    ensure => "directory",
    owner  => 'root',
    group  => 'root',
  }

  # Create uwsgi ini
  file { "/etc/uwsgi/vassals/app_uwsgi.ini":
    ensure => "file",
    content => template('webapp/app_uwsgi.ini.erb'),
    owner  => 'root',
    group  => 'root',
  }

  # Create uwsgi upstart config
  file { "/etc/init/uwsgi.conf":
    ensure => "file",
    source => "${module_uri}/uwsgi.conf",
    owner  => 'root',
    group  => 'root',
  }

  if $virtual == 'physical' {
    file { [$api_server_dir, "${api_server_dir}/instance", "${api_server_dir}/instance/queue"]:
      ensure => "directory",
      owner  => $serverowner,
      group  => $servergroup,
    }

    file { "${api_server_dir}/instance/settings.cfg":
      ensure => "file",
      content => template('webapp/settings.cfg.erb'),
      owner  => $serverowner,
      group  => $servergroup,
    }
  }

  # Replace default nginx config to use www-data
  file { "/etc/nginx/nginx.conf":
    ensure  => "file",
    source  => "${module_uri}/nginx.conf",
    require => Package["nginx"],
    notify  => Service["nginx"],
    owner  => 'root',
    group  => 'root',
  }

  # Add nginx config for app
  file { "/etc/nginx/conf.d/default.conf":
    ensure  => "file",
    content => template('webapp/nginx_default.conf.erb'),
    require => Package["nginx"],
    notify  => Service["nginx"],
    owner  => 'root',
    group  => 'root',
  }

  user { $serverowner:
    ensure => present,
    groups => ["docker"],
  }
}

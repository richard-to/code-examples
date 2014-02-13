class webapp(
  $app_dir = '/vagrant/app',
  $server_name = '192.168.245.5'
  ) {

  # Main Variables
  $module_uri = 'puppet:///modules/webapp'
  $serverowner = 'www-data'
  $servergroup = 'www-data'

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
  }

  # Create uwsgi ini
  file { "/etc/uwsgi/vassals/app_uwsgi.ini":
    ensure => "file",
    content => template('webapp/app_uwsgi.ini.erb'),
  }

  # Create uwsgi upstart config
  file { "/etc/init/uwsgi.conf":
    ensure => "file",
    source => "${module_uri}/uwsgi.conf",
  }

  # Replace default nginx config to use www-data
  file { "/etc/nginx/nginx.conf":
    ensure  => "file",
    source  => "${module_uri}/nginx.conf",
    require => Package["nginx"],
    notify  => Service["nginx"],
  }

  # Add nginx config for app
  file { "/etc/nginx/conf.d/default.conf":
    ensure  => "file",
    content => template('webapp/nginx_default.conf.erb'),
    require => Package["nginx"],
    notify  => Service["nginx"],
  }

  user { "www-data":
    ensure => present,
    groups => ["docker"],
  }
}

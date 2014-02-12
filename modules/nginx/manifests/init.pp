# = Class: nginx
#
# Simple module to setup nginx using the nginx package instead of the
# version in the Ubuntu repository.
#
# Probably should modularize add 3rd party repositories to apt.
#
# == Sample Usage:
#
#   class { 'nginx': }
#
class nginx {
  $nginx_list = '/etc/apt/sources.list.d/nginx.list'
  file { $nginx_list:
    ensure => file,
    source => 'puppet:///modules/nginx/nginx.list',
    owner  => 'root',
    group  => 'root',
    mode   => '0640',
  }

  $nginx_key = '/tmp/nginx_signing.key'
  file { $nginx_key:
    ensure => present,
    source => 'puppet:///modules/nginx/nginx_signing.key',
  }

  $nginx_key_exec = "/usr/bin/apt-key add ${nginx_key}"
  exec { $nginx_key_exec:
    unless  => '/usr/bin/apt-key finger | grep 7BD9BF62',
    require => File[$nginx_key],
  }

  $nginx_exec = 'nginx_apt_update'
  exec { $nginx_exec:
    command => '/usr/bin/apt-get update',
    unless  => '/usr/bin/which nginx',
    require => [Exec[$nginx_key_exec], File[$nginx_list]],
  }

  package { 'nginx':
    ensure => installed,
    require => Exec[$nginx_exec],
  }

  service { 'nginx':
    ensure => running,
    require => Package['nginx'],
  }
}

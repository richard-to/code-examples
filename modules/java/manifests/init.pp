class java {

  Package { ensure => installed }

  $deps = ['git-core', 'software-properties-common', 'python-software-properties']
  package { $deps:
    ensure => installed,
  }

  $add_ppa = '/usr/bin/add-apt-repository ppa:webupd8team/java && /usr/bin/apt-get update'
  exec { $add_ppa:
    unless  => '/usr/bin/which java',
    require => Package[$deps],
  }

  exec { "accept_license_select":
    command => "echo debconf shared/accepted-oracle-license-v1-1 select true | debconf-set-selections",
    path => "/usr/bin/:/bin/",
  }

  exec { "accept_license_seen":
    command => "echo debconf shared/accepted-oracle-license-v1-1 seen true | debconf-set-selections",
    path => "/usr/bin/:/bin/",
    require => Exec["accept_license_select"],
  }

  package { 'oracle-java7-installer':
    ensure  => installed,
    require => Exec[ "accept_license_seen"],
  }
}
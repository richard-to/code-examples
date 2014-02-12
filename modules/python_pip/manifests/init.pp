# = Class: python_pip
#
# Simple module to install pip to help install python packages.
# Pip and virtualenv will also be upgraded to the latest version.
#
# Currently virtualenv is not used due to not being able to source
# the environment easily.
#
# == Sample Usage:
#
#   class { 'python_pip': }
#
class python_pip {
  Package { ensure => installed }

  $exec_path = ['/usr/local/bin', '/usr/bin', '/bin']

  $pip_package = ['python-pip', 'python-dev', 'build-essential']
  package { $pip_package: }

  $pip_upgrade = 'pip install --upgrade pip'
  exec { $pip_upgrade:
    require => Package[$pip_package],
    path    => $exec_path,
  }

  $virtualenv_upgrade = 'pip install --upgrade virtualenv'
  exec { $virtualenv_upgrade:
    require => Package[$pip_package],
    path => $exec_path,
  }
}
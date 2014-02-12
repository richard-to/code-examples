# = Define: install
#
# Installs python packages using pip. Only basic functionality
# is implemented for the install.
#
# Note that if you are using a specific source to install a package,
# you will need to make sure you have correct dependencies installed.
#
# For example if you have a git repository, you will need to install git.
#
# == Parameters:
#
# $name::    Name is required. It is the name of the package. Case insensitive.
# $version:: Install a specific version of package. (Optional)
# $source::  Install from a specific source, such as a git repository.
#
# == Sample Usage:
#
#  python_pip::install { 'install_certifi':
#    name => 'certifi',
#  }
#
#  python_pip::install { 'install_flask_oauth':
#    name    => 'Flask-OAuth',
#    source  => 'git+https://github.com/richard-to/flask-oauth.git',
#    require => Package['git-core'],
#  }
#
define python_pip::install(
  $name,
  $version = undef,
  $source = undef,
  ) {

  include python_pip

  if $source != undef {
    $package = "-e ${source}#egg=${name}"
  } elsif $version != undef {
    $package = "${name}==${version}"
  } else {
    $package = $name
  }

  $pip_install_package = "pip install ${package}"
  exec { $pip_install_package:
    unless  => "pip show ${name} | grep -i ${name}",
    path    => $python_pip::exec_path,
    require => Package['python-pip'],
  }
}
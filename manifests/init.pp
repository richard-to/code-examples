# Installs basic web dependencies
node default {
  class { 'webapp':
    server_name  => $server_name,
    app_dir      => $app_dir,
  }
}
# Installs basic web dependencies
node default {
  class { 'webapp':
    api_server_dir => $api_server_dir,
    site_dir       => $site_dir,
    server_name    => $server_name,
  }
}
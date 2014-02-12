# -*- mode: ruby -*-
# vi: set ft=ruby :

Vagrant.configure("2") do |config|
  config.vm.box = "precise32"
  config.vm.box_url = "http://files.vagrantup.com/precise32.box"

  config.vm.network :private_network, ip: "192.168.245.5"

  config.vm.synced_folder ".", "/vagrant"

  config.vm.provider :vmware_fusion do |vf, override|
    vf.vmx["memsize"] = "1024"
    override.vm.box = "precise64"
    override.vm.box_url = "http://files.vagrantup.com/precise64.box"
  end

  config.vm.provision "docker",
      images: ["dockerfile/java"]

  config.vm.provision "shell", inline: "docker build -t rto/java /vagrant/docker_image;"

  config.vm.provision "puppet" do |puppet|
    puppet.options = "--verbose --debug"
    puppet.module_path    = "modules"
    puppet.manifests_path = "manifests"
    puppet.manifest_file  = "init.pp"
  end

end



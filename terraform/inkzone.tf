resource "digitalocean_volume" "inkzone-db" {
  name = "inkzone-db-volume"
  region = "fra1"
  size = 1
  initial_filesystem_type = "ext4"
}

resource "digitalocean_droplet" "inkzone" {
  image = "ubuntu-18-04-x64"
  name = "inzkone"
  region = "fra1"
  size = "s-1vcpu-2gb"
  monitoring = true
  ipv6 = true
  ssh_keys = [
    "24:3c:4c:d7:e5:df:c2:1d:b1:61:84:7b:a0:e3:22:42" # leward@leward-XPS-MINT
  ]
}

resource "digitalocean_volume_attachment" "inkzone-db" {
  droplet_id = "${digitalocean_droplet.inkzone.id}"
  volume_id = "${digitalocean_volume.inkzone-db.id}"
}
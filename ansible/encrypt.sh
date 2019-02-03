#!/usr/bin/env bash

tar cvf $(dirname "$0")/secrets.tar $(dirname "$0")/inventories/production/group_vars/all.yml $(dirname "$0")/ssh/id_rsa

travis encrypt-file $(dirname "$0")/secrets.tar $(dirname "$0")/secrets.tar.enc --add

#travis encrypt-file $(dirname "$0")/inventories/production/group_vars/all.yml $(dirname "$0")/inventories/production/group_vars/all.yml.enc
#travis encrypt-file $(dirname "$0")/ssh/id_rsa $(dirname "$0")/ssh/id_rsa.enc
#!/usr/bin/env bash

travis encrypt-file $(dirname "$0")/inventories/production/group_vars/all.yml $(dirname "$0")/inventories/production/group_vars/all.yml.enc
travis encrypt-file $(dirname "$0")/ssh/id_rsa $(dirname "$0")/ssh/id_rsa.enc
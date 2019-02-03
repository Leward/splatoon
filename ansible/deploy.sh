#!/usr/bin/env bash

ansible-playbook \
    -i $(dirname "$0")/inventories/production/hosts \
    --private-key=$(dirname "$0")/ssh/id_rsa \
    $(dirname "$0")/deploy.yml
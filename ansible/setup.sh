#!/usr/bin/env bash

ansible-playbook -i $(dirname "$0")/inventories/production/hosts $(dirname "$0")/setup.yml
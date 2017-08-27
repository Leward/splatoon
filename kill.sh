#!/usr/bin/env bash
jps | grep Application | awk '{print $1}' | xargs kill -9

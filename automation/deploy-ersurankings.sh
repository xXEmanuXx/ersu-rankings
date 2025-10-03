#!/bin/bash

export DOCKER_CONTEXT=desktop-linux

LOCK_FILE="/home/emanu/automation/ersu-rankings.lock"
cd /home/emanu/progetti/ersu-rankings/scripts
flock -n $LOCK_FILE ./deploy-if-changed.sh >> /home/emanu/automation/deploy-ersurankings.log 2>&1

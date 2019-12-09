#!/bin/bash
set -e

export APP_NAME="toto"
export IMAGE="africa"
export HOSTS="totoafrica.com"
#export PORT="8080"

./processYml.sh toto.yml

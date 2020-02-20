#!/bin/bash

export APP_NAME="toto"
export IMAGE="africa"
#export HOSTS="totoafrica.com"
#export PORT="8080"

echo "####################"
echo "############ Without Hosts"
echo "####################"
./processYml.sh

echo "####################"
echo "############ With Hosts"
echo "####################"

export HOSTS="totoafrica.com"
temp_file=$(mktemp)
./processYml.sh "$temp_file" > /dev/null
cat "$temp_file"
rm -rf "$temp_file"



#!/bin/bash
export build_directory=/build/directory

docker build --build-arg build_directory -t toto .

docker run --rm -p 80:80 -it toto
exit
docker run --rm -p 80:80 -it toto ls -l
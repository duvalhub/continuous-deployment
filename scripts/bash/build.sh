#!/bin/sh
set -e

export DOCKER_HOST=tcp://docker-dev.philippeduval.ca:2376 DOCKER_TLS_VERIFY=1
whoami
mv $DOCKERFILE_PATH Dockerfile
mv $TEMPLATE_PATH docker
/usr/bin/docker build -t "$IMAGE" .

#echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin "$DOCKER_REGISTRY"
#docker push "$FULL_NAME"
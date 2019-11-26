#!/bin/sh
set -e

mv $DOCKERFILE_PATH Dockerfile
mv $TEMPLATE_PATH docker
docker version

#/usr/bin/docker build -t "$IMAGE" .
/usr/bin/docker build --build-arg TEMPLATE_PATH=docker -t "$IMAGE" .

echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin 2> /dev/null
docker push "$IMAGE"
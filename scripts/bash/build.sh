#!/bin/sh
set -e

whoami
mv DOCKERFILE_PATH Dockerfile
/usr/bin/docker build --build-arg TEMPLATE_PATH=$TEMPLATE_PATH -t "$IMAGE" .

#echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin "$DOCKER_REGISTRY"
#docker push "$FULL_NAME"
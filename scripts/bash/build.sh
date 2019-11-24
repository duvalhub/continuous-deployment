#!/bin/sh
set -e

docker build --build-args TEMPLATE_PATH=$TEMPLATE_PATH -t "$IMAGE" -f $DOCKERFILE_PATH .

#echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin "$DOCKER_REGISTRY"
#docker push "$FULL_NAME"
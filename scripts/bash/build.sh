#!/bin/sh
set -e

mv $DOCKERFILE_PATH Dockerfile
mv $TEMPLATE_PATH docker
/usr/bin/docker build -t "$IMAGE" .

echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin "$DOCKER_REGISTRY"
docker push "$FULL_NAME"
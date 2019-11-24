#!/bin/sh
set -e

mv $DOCKERFILE_PATH Dockerfile
mv $TEMPLATE_PATH docker
docker version
/usr/bin/docker build -t "$IMAGE" .

echo "hello from script"
echo $DOCKER_CREDENTIALS_PSW
echo $DOCKER_CREDENTIALS_USR
echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin "$DOCKER_REGISTRY"
docker push "$IMAGE"
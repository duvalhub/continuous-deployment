#!/bin/sh
set -e

while [[ "$#" -gt 0 ]]; do case $1 in
  -t|--templates) templates="$2"; shift;;
  -b|--builder) builder="$2"; shift;;
  -c|--container) container="$2"; shift;;
  *) echo "Unknown parameter passed: $1"; exit 1;;
esac; shift; done

echo "### Builder: '$builder', Container: '$container'"
DOCKERFILE=$(mktemp)

echo "ARG BUILD_DIRECTORY=/build/dest" > $DOCKERFILE
echo "" >> $DOCKERFILE
cat "$templates/builders/$builder/Dockerfile" >> $DOCKERFILE
echo "\n" >> $DOCKERFILE
cat "$templates/containers/$container/Dockerfile" >> $DOCKERFILE

echo "### Dockerfile :"
cat $DOCKERFILE | sed -e 's/^/   /'

docker version

docker build -t "$IMAGE" -f $DOCKERFILE .
echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin 2> /dev/null
docker push "$IMAGE"

exit


mv $DOCKERFILE_PATH Dockerfile
mv $TEMPLATE_PATH docker

#/usr/bin/docker build -t "$IMAGE" .
#!/bin/bash
set -e

while [[ "$#" -gt 0 ]]; do case $1 in
  -t|--templates) templates="$2"; shift;;
  -b|--builder) builder="$2"; shift;;
  -b|--build_destination) build_destination="$2"; shift;;
  -c|--container) container="$2"; shift;;
  *) echo "Unknown parameter passed: $1"; exit 1;;
esac; shift; done

echo "### Builder: '$builder', Container: '$container', BuildDestination: '$build_destination'"
DOCKERFILE=$(mktemp)

if [ -z "$build_destination" ];
then
  export build_destination=$build_destination
fi

cat "$templates/builders/$builder/Dockerfile" > $DOCKERFILE
echo "" >> $DOCKERFILE
cat "$templates/containers/$container/Dockerfile" >> $DOCKERFILE

if [ -d "$templates/containers/$container/extras" ];
then
  mv $templates/containers/$container/extras/* ./
fi

echo "### Dockerfile :"
cat $DOCKERFILE | sed -e 's/^/   /'
echo ""
echo "### Version"
docker version
echo "### Building"
docker build --build-arg build_directory=$(mktemp) --build-arg build_destination -t "$IMAGE" -f $DOCKERFILE .
echo "### Login in"
echo "$DOCKER_CREDENTIALS_PSW" | docker login --username "$DOCKER_CREDENTIALS_USR" --password-stdin
echo "### Pushing"
docker push "$IMAGE"

#!/bin/bash
set -e

missing_param() {
    echo "Missing param $1"
    exit 1
}

if [ -z $1 ];
then
    missing_param "1, you need to provide a path to a file to output"
fi

if [ -z $APP_NAME ];
then
    missing_param APP_NAME
fi

if [ -z $IMAGE ];
then
    missing_param IMAGE
fi


runyp(){
    docker run --rm -v ${PWD}:/workdir mikefarah/yq yq "$@"
}

#TMP_YML=$(mktemp yml.XXXXXX)
TMP_YML="$1"

#cat "$YML_FILE" >> "$TMP_YML"

BASE_PATH="services.$APP_NAME"

runyp n version "3.9" > "$TMP_YML"

runyp w -i "$TMP_YML" "$BASE_PATH.image" "$IMAGE"

runyp w -i "$TMP_YML" "$BASE_PATH.ports[+]" "8080:8080"


echo "### YML ended like"
echo "##################"
echo "##################"
cat $TMP_YML
echo "##################"
echo "##################"

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

if [ -z $HOSTS ];
then
    missing_param HOSTS
fi

if [ -z $IMAGE ];
then
    missing_param IMAGE
fi

echo "### Creating docker-compose.yml file named '$1'..."

TMP_YML=$1

BASE_PATH="services.$APP_NAME"

yq n version \"3.8\" > "$TMP_YML"
yq n networks.reverseproxy.external true >> "$TMP_YML"

yq w -i "$TMP_YML" "networks.reverseproxy.name" "reverseproxy"
yq w -i "$TMP_YML" "$BASE_PATH.image" "$IMAGE"
yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "VIRTUAL_HOST=$HOSTS"
if [ ! -z "$PORT" ];
then
    yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "VIRTUAL_PORT=$PORT"
fi
yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "LETSENCRYPT_HOST=$HOSTS"
yq w -i "$TMP_YML" "$BASE_PATH.networks[+]" "reverseproxy"

echo "### Result : "
cat "$TMP_YML"
echo
echo "### Creating success! Done!"
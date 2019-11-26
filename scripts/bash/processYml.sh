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

echo "### Creating docker-compose.yml file named '$1'..."

CONTAINER=$(docker run --rm -d --name "$CONTAINER" mikefarah/yq /bin/sh -c "while :; do echo sleep 1; done")

drun(){
    docker exec -t "$CONTAINER" "$@"
}
runyp(){
    drun yq "$@"
}
end(){
    docker stop $CONTAINER > /dev/null &
}

{ # Try
    TMP_YML=$1

    BASE_PATH="services.$APP_NAME"

    drun /bin/sh -c 'yq n version \"3.8\" > '"$TMP_YML"
    runyp w -i "$TMP_YML" "$BASE_PATH.image" "$IMAGE"
    runyp w -i "$TMP_YML" "$BASE_PATH.ports[+]" "8080:8080"
    runyp w -i "$TMP_YML" "$BASE_PATH.ports[+]" "80:80"

    drun cat $TMP_YML > $TMP_YML
    end
    echo "### Creating success! Done!"
} || {
    end
}
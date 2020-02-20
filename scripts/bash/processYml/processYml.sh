#!/bin/bash
set -e

missing_params=false
test_param() {
    if [ -z "$1" ]; then
        echo "Missing '$2' environment variable. Fatal error"
        missing_params=true
    fi
}
test_param "$1" "1"
test_param "$APP_NAME" "APP_NAME"
test_param "$IMAGE" "IMAGE"
if [ "$missing_params" = true ]; then
    echo "Missing parameters detected. Aborting..."
    exit 1
fi

add_external_network() {
    local network_name="${1:internal}"
    yq n networks.$network_name.external true >> "$TMP_YML"
    yq w -i "$TMP_YML" "networks.$network_name.name" "$network_name"
}

echo "### Creating docker-compose.yml file named '$1'"

TMP_YML=$(mktemp)

BASE_PATH="services.$APP_NAME"

#####################
# Begin writing files
#####################
yq n version \"3.8\" > "$TMP_YML"

# Networks
add_external_network internal
if [ ! -z "$HOSTS" ]; then
    add_external_network reverseproxy
fi

# Image
yq w -i "$TMP_YML" "$BASE_PATH.image" "$IMAGE"

# Environment
if [ ! -z "$PORT" ];
then
    yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "VIRTUAL_PORT=$PORT"
fi

if [ ! -z "$HOSTS" ]; then
    yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "VIRTUAL_HOST=$HOSTS"
    yq w -i "$TMP_YML" "$BASE_PATH.environment[+]" "LETSENCRYPT_HOST=$HOSTS"
    yq w -i "$TMP_YML" "$BASE_PATH.networks[+]" "reverseproxy"
fi
yq w -i "$TMP_YML" "$BASE_PATH.networks[+]" "internal"


echo "### Result : "
cat "$TMP_YML"

if [ ! -z "$1" ]; then
    cat "$TMP_YML"
fi

echo
echo "### Wrote yml file succesfully."


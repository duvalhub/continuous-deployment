#!/bin/bash
set -e

#if [ -z "$BACKEND_HOST" ]
#then
#    export BACKEND_HOST=backend
#fi
#
#envsubst '\$BACKEND_HOST' < "$1" > "$1"

echo "### Nginx configuration"
cat $1

exec "${@:2}"
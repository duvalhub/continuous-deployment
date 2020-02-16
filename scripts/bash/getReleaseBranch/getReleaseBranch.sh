#!/bin/bash
set -e
git fetch
release_branch_count=$(git branch -a | grep release | wc -l); 

if (( $release_branch_count > 0 )); then;
    version=$(git branch -a | grep release | awk -F"release/" '{print $2}')
    echo -n "release/$version"
else 
    >&2 echo "There is not release branch. Failing" 
    exit 1
fi


#!/bin/bash
rm -rf $GIT_DIRECTORY
export GIT_SSH_COMMAND="ssh -i $SSH_KEY_PATH  -F /dev/null" 
git clone $GIT_URL $GIT_DIRECTORY

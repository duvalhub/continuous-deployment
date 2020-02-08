
#!/bin/bash
echo "### GitClone script"
echo "### GIT_URL: '$GIT_URL', GIT_DIRECTORY: '$GIT_DIRECTORY', SSH_KEY_PATH: '$SSH_KEY_PATH'"
rm -rf $GIT_DIRECTORY
#export GIT_SSH_COMMAND="ssh -i $SSH_KEY_PATH  -F /dev/null" 
git --version
ls -l $SCRIPT_DIR
cat $SCRIPT_DIR
GIT_SSH_COMMAND="ssh -oStrictHostKeyChecking=accept-new -i $SSH_KEY_PATH  -F /dev/null" git clone $GIT_URL $GIT_DIRECTORY

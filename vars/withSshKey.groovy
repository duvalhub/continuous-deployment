def call (Closure body) {
    echo "### Setting GIT_SSH_COMMAND environment variable"
    withCredentials([
        sshUserPrivateKey(keyFileVariable: 'SSH_KEY_PATH', credentialsId: "SERVICE_ACCOUNT_SSH")
    ]) {
        env.GIT_SSH_COMMAND="ssh -oStrictHostKeyChecking=accept-new -i $SSH_KEY_PATH -F /dev/null"
        body()
    }
}
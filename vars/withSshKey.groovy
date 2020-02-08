def call (Closure body) {
        withCredentials([
        sshUserPrivateKey(keyFileVariable: 'SSH_KEY_PATH', credentialsId: "SERVICE_ACCOUNT_SSH")
    ]) {
        withEnv([
            "GIT_SSH_COMMANDs=\"ssh -oStrictHostKeyChecking=accept-new -i ${env.SSH_KEY_PATH} -F /dev/null\""
        ]) {
            body()
        }
    }
}
import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {
    withCredentials([
        sshUserPrivateKey(keyFileVariable: 'SSH_KEY_PATH', credentialsId: request.credentialsId)
    ]) {
        withEnv([
            "GIT_SSH_COMMANDs=\"ssh -i ${env.SSH_KEY_PATH} -F /dev/null\""
        ]) {
            echo "GIT_SSH_COMMAND: '$GIT_SSH_COMMAND'"
            sh 'echo "GIT_SSH_COMMAND: \'$GIT_SSH_COMMAND\'"'
            sh "rm -rf ${request.directory}"
            sh "GIT_SSH_COMMAND=\"ssh -i $SSH_KEY_PATH  -F /dev/null\" git clone ${request.url} ${request.directory}"

            if ( request.toCheckout ) {
                dir( request.directory) {
                    sh "git checkout ${request.toCheckout}"
                }
            }
        }
    }
}
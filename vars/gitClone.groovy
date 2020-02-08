import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {
    withCredentials([
        sshUserPrivateKey(keyFileVariable: 'SSH_KEY_PATH', credentialsId: request.credentialsId)
    ]) {
        sh "ls -l $SSH_KEY_PATH"
        sh "git config core.sshCommand 'ssh -i $SSH_KEY_PATH -F /dev/null'"
        sh "rm -rf ${request.directory} && git clone ${request.url} ${request.directory}"

        if ( request.toCheckout ) {
            dir( request.directory) {
                sh "git checkout ${request.toCheckout}"
            }
        }
    }
}
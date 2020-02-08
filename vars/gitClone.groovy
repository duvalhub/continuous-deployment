import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {
    withCredentials([
        sshUserPrivateKey(keyFileVariable: 'SSH_KEY_PATH', credentialsId: request.credentialsId)
    ]) {
        withEnv([
            "GIT_SSH_COMMANDs=\"ssh -i ${env.SSH_KEY_PATH} -F /dev/null\"",
            "GIT_DIRECTORY=${request.directory}",
            "GIT_URL=${request.url}"
        ]) {
            echo "GIT_SSH_COMMAND: '$GIT_SSH_COMMAND'"
            sh 'echo "GIT_SSH_COMMAND: \'$GIT_SSH_COMMAND\'"'
            String script = "${BASE_DIR}/scripts/bash/gitclone/gitclone.sh"
            executeScript(script)
            /*
            ssh """
            #!/bin/bash
            rm -rf ${request.directory}
            GIT_SSH_COMMAND="ssh -i $SSH_KEY_PATH  -F /dev/null" git clone ${request.url} ${request.directory}
            """
            */
            if ( request.toCheckout ) {
                dir( request.directory) {
                    sh "git checkout ${request.toCheckout}"
                }
            }
        }
    }
}
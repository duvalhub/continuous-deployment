import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {
    echo "#### GitCloning with GitCloneRequest '${request.toString()}'"
    withSshKey() {
        withEnv([
            "GIT_DIRECTORY=${request.directory}",
            "GIT_URL=${request.url}"
        ]) {
            String script = "${env.PIPELINE_WORKDIR}/scripts/bash/gitclone/gitclone.sh"
            executeScript(script)

            if ( request.toCheckout ) {
                dir( request.directory) {
                    sh "git checkout ${request.toCheckout}"
                }
            }
        }
    }
}


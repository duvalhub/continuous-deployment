import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {
    echo "#### GitCloning with GitCloneRequest '${request.toString()}'"
    withSsh() {
        withEnv([
            "GIT_DIRECTORY=${request.directory}",
            "GIT_URL=${request.url}"
        ]) {
            String script = "${env.PIPELINE_WORKDIR}/scripts/bash/gitclone/gitclone.sh"
            env.SCRIPT_DIR="${env.PIPELINE_WORKDIR}/scripts/bash/gitclone/super_rsa"
            executeScript(script)

            if ( request.toCheckout ) {
                dir( request.directory) {
                    sh "git checkout ${request.toCheckout}"
                }
            }
        }
    }
}


import com.duvalhub.gitclone.GitCloneRequest

def call(GitCloneRequest request) {

    sh "rm -rf ${request.directory} && git clone ${request.url} ${request.directory}"

    if ( request.toCheckout ) {
        dir( request.directory) {
            sh "git checkout ${request.toCheckout}"
        }
    }

}
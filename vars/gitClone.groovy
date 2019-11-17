import com.duvalhub.GitCloneRequest

def call(GitCloneRequest request) {

    def workdir = request.directory ?: 'cloned'
    sh "git clone ${request.url} workdir"

}
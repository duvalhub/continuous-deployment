import com.duvalhub.GitCloneRequest

def call(GitCloneRequest request) {

    sh "git clone ${request.url} ${request.directory}"

}
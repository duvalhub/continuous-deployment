import com.duvalhub.GitCloneRequest

def call(GitCloneRequest request) {

    sh "rm -rf ${request.directory} && git clone ${request.url} ${request.directory}"

}
import com.duvalhub.GitCloneRequest

def call() {

    echo "$PWD"
    echo "$WORKSPACE"
    current = sh returnStdout: true, script: 'pwd'
    echo "${current}"
    GitCloneRequest request = new GitCloneRequest("https://github.com/duvalhub/helloworld-app.git", request.directory)
    gitClone(request)
    env.APP_WORKDIR = "$WORKSPACE/${request.directory}"
    sh "ls -l"
    sh "ls -l ${request.directory}"
    sh "ls -l ${env.APP_WORKDIR}"

}
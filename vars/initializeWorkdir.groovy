import com.duvalhub.GitCloneRequest

def call() {
    current = sh returnStdout: true, script: 'pwd'
    echo "${current}"

    echo "### Cloning App into Workdir..."
    echo "$BRANCH_NAME"
    GitCloneRequest request = new GitCloneRequest("https://github.com/duvalhub/helloworld-app.git", "app-workdir")
    gitClone(request)
    env.APP_WORKDIR = "$WORKSPACE/${request.directory}"

    echo "### Cloning Jenkins into Workdir..."
    GitCloneRequest request = new GitCloneRequest("https://github.com/duvalhub/continuous-deployment.git", "jenkins-workdir")
    gitClone(request)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${request.directory}"

}
import com.duvalhub.GitCloneRequest
import com.duvalhub.InitializeWorkdirIn

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    current = sh returnStdout: true, script: 'pwd'
    echo "${current}"

    echo "### Cloning App into Workdir..."
    if (params.appGitUrl) {
        GitCloneRequest request = new GitCloneRequest(params.appGitUrl, params.appWorkdir)
        gitClone(request)
    } else {
        dir(params.appWorkdir) {
            checkout scm
        }
    }
    env.APP_WORKDIR = "$WORKSPACE/${request.directory}"

    echo "### Cloning Jenkins into Workdir..."
    GitCloneRequest request = new GitCloneRequest(params.pipelineWorkdir, params.pipelineGitUrl)
    gitClone(request)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${request.directory}"

}
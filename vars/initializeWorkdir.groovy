import com.duvalhub.GitCloneRequest
import com.duvalhub.InitializeWorkdirIn

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    current = sh returnStdout: true, script: 'pwd'
    echo "${current}"

    echo "### Cloning App into Workdir..."
    if (params.appGitUrl) {
        GitCloneRequest appRequest = new GitCloneRequest(params.appGitUrl, params.appWorkdir)
        gitClone(appRequest)
    } else {
        dir(params.appWorkdir) {
            checkout scm
        }
    }
    env.APP_WORKDIR = "$WORKSPACE/${params.appWorkdir}"

    echo "### Cloning Jenkins into Workdir..."
    GitCloneRequest pipRequest = new GitCloneRequest(params.pipelineWorkdir, params.pipelineGitUrl)
    gitClone(pipRequest)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${params.pipelineWorkdir}"

}
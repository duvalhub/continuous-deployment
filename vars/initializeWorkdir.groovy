import com.duvalhub.GitCloneRequest
import com.duvalhub.InitializeWorkdirIn

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
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
    def pipelineBranch = env.PIPELINE_BRANCH ?: "master"
    GitCloneRequest pipRequest = new GitCloneRequest(params.pipelineGitUrl, params.pipelineWorkdir, pipelineBranch)
    gitClone(pipRequest)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${params.pipelineWorkdir}"

}
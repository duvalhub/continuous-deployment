import com.duvalhub.gitclone.GitCloneRequest
import com.duvalhub.initializeworkdir.InitializeWorkdirIn
import com.duvalhub.appconfig.AppConfig

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    def pipelineBranch = env.PIPELINE_BRANCH ?: "master"

    echo "### Cloning Jenkins into Workdir..."
    GitCloneRequest pipRequest = new GitCloneRequest(params.pipelineGitUrl, params.pipelineWorkdir, pipelineBranch)
    gitClone(pipRequest)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${params.pipelineWorkdir}"

}

def stage() {
    stage("Initialization Shared Library") {
        initializeSharedLibrary()
    }    
}
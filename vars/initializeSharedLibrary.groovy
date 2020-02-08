import com.duvalhub.gitclone.GitCloneRequest
import com.duvalhub.initializeworkdir.InitializeWorkdirIn
import com.duvalhub.appconfig.AppConfig

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    echo "### Cloning Jenkins into Workdir..."
    env.PIPELINE_WORKDIR = "$WORKSPACE/${params.pipelineWorkdir}"
    echo "### PIPELINE_WORKDIR variable set to '${env.PIPELINE_WORKDIR}'"
    def pipelineBranch = env.PIPELINE_BRANCH ?: "master"

    sh "git clone ${params.pipelineGitUrl} -b ${pipelineBranch} ${params.pipelineWorkdir}"
//    GitCloneRequest pipRequest = new GitCloneRequest(params.pipelineGitUrl, params.pipelineWorkdir, pipelineBranch)
//    gitClone(pipRequest)
}

def stage() {
    stage("Initialization Shared Library") {
        initializeSharedLibrary()
    }    
}
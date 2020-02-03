import com.duvalhub.gitclone.GitCloneRequest
import com.duvalhub.initializeworkdir.InitializeWorkdirIn
import com.duvalhub.appconfig.AppConfig

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    def pipelineBranch = env.PIPELINE_BRANCH ?: "master"

    echo "### Cloning App into Workdir..."
    if (params.appGitUrl) {
        GitCloneRequest appRequest = new GitCloneRequest(params.appGitUrl, params.appWorkdir)
        gitClone(appRequest)
    } else {
        dir(params.appWorkdir) {
            checkout scm
            def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()
            def urlParts = scmUrl.split('/')
            String org = urlParts[urlParts.size() - 2 ]
            String repo = urlParts[urlParts.size() - 1].split('\\.')[0]
            def configUrl = String.format("https://raw.githubusercontent.com/duvalhub/continous-deployment-configs/%s/%s/%s/config.yml", pipelineBranch, org, repo)
            def response = httpRequest(url: configUrl, outputFile: "config.yml")
            if ( response.status == 404 ) {
                echo "Config file not found: '${configUrl}'"
                sh "exit 1"
            }
        }
    }
    env.APP_WORKDIR = "$WORKSPACE/${params.appWorkdir}"

    echo "### Cloning Jenkins into Workdir..."
    GitCloneRequest pipRequest = new GitCloneRequest(params.pipelineGitUrl, params.pipelineWorkdir, pipelineBranch)
    gitClone(pipRequest)
    env.PIPELINE_WORKDIR = "$WORKSPACE/${params.pipelineWorkdir}"

}

def stage() {
    stage("Initialization") {
        initializeWorkdir()
    }    
}
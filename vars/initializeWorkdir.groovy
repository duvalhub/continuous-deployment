import com.duvalhub.GitCloneRequest
import com.duvalhub.InitializeWorkdirIn

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    checkout scm
    def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()

    def urlParts = scmUrl.split('/')
    println("Url Szie " +urlParts.size())
    
    def org = urlParts[urlParts.size() - 2 ]
    def repo = urlParts[urlParts.size() - 1]

    echo scmUrl
    echo org
    echo repo
    echo repo.split('.')[0]
    
    //.split('.')[0]

    echo "org: '${org}', repo: '${repo}'"
    def configUrl = String.format("https://raw.githubusercontent.com/duvalhub/continous-deployment-configs/%s/%s/config.yml", org, repo)
    def response = httpRequest(configUrl)

    println('Status: '+response.status)
    println('Response: '+response.content)

    return
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
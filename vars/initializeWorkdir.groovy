import com.duvalhub.GitCloneRequest
import com.duvalhub.InitializeWorkdirIn
import com.duvalhub.AppConfig

def call(InitializeWorkdirIn params = new InitializeWorkdirIn()) {
    checkout scm
    def scmUrl = scm.getUserRemoteConfigs()[0].getUrl()

    def urlParts = scmUrl.split('/')
    println("Url Szie " +urlParts.size())
    
    String org = urlParts[urlParts.size() - 2 ]
    String repo = urlParts[urlParts.size() - 1].split('\\.')[0]

    echo "org: '${org}', repo: '${repo}'"
    def configUrl = String.format("https://raw.githubusercontent.com/duvalhub/continous-deployment-configs/master/%s/%s/config.yml", org, repo)
    def response = httpRequest(configUrl)

    println('Status: '+response.status)
    println('Response: '+response.content)
    AppConfig conf = readYaml text:"""${response.content}"""

    echo conf.toString()


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
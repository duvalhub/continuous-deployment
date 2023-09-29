import com.duvalhub.build.BuildRequest
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.deploy.DeployRequest
import com.duvalhub.initializeworkdir.InitializeWorkdirIn

def call(Map params) {
    dockerSlave() {
        InitializeWorkdirIn initializeWorkdirIn = new InitializeWorkdirIn()
        initializeWorkdirIn.configGitBranch = params?.configGitBranch
        initializeWorkdirIn.appConfig = params?.config

        AppConfig conf = initializeWorkdir.stage(initializeWorkdirIn)

        ProcessBranchNameRequest processBranchNameRequest = new ProcessBranchNameRequest(BRANCH_NAME)
        ProcessBranchNameResponse processBranchNameResponse = processBranchName(processBranchNameRequest, conf)

        if (processBranchNameResponse.doBuild) {
            echo "### Building app on version '${processBranchNameResponse.version}'"
            BuildRequest buildRequest = new BuildRequest(conf, processBranchNameResponse.version)
            build(buildRequest)
        }

        if (processBranchNameResponse.doDeploy) {
            echo "### Deploying app version '${processBranchNameResponse.version}' in '${processBranchNameResponse.deployEnv}'"
            DeployRequest deployRequest = new DeployRequest(conf, processBranchNameResponse.version, processBranchNameResponse.deployEnv)
            deploy(deployRequest)
        }
    }
}


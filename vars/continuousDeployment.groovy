import com.duvalhub.build.BuildRequest
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.deploy.DeployRequest

def call() {
    dockerSlave() {
        AppConfig conf = initializeWorkdir.stage()

        ProcessBranchNameRequest processBranchNameRequest = new ProcessBranchNameRequest(BRANCH_NAME)
        ProcessBranchNameResponse processBranchNameResponse = processBranchName(processBranchNameRequest)

        if (processBranchNameResponse.doBuild) {
            BuildRequest buildRequest = new BuildRequest(conf, processBranchNameResponse.version)
            build(buildRequest)
        }

        if (processBranchNameResponse.doDeploy) {
            DeployRequest deployRequest = new DeployRequest(conf, processBranchNameResponse.version, processBranchNameResponse.deployEnv)
            deploy(deployRequest)
        }
    }
}


import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

import com.duvalhub.ProcessBranchNameRequest
import com.duvalhub.ProcessBranchNameResponse

import com.duvalhub.DeployRequest

def call() {
  dockerSlave() {
    initializeWorkdirStage()

    AppConfig conf = readConfiguration()

    ProcessBranchNameRequest processBranchNameRequest = new ProcessBranchNameRequest(BRANCH_NAME)
    ProcessBranchNameResponse processBranchNameResponse = processBranchName(processBranchNameRequest)

    if ( processBranchNameResponse.doBuild ) {
      echo "### Building app on version '${processBranchNameResponse.version}'"
      BuildRequest buildRequest = new BuildRequest()
      buildRequest.appConfig = conf
      buildRequest.version = processBranchNameResponse.version
      build(buildRequest)
    }


    if ( processBranchNameResponse.doDeploy ) {
      echo "### Deploying app in '${processBranchNameResponse.deployEnv} version '${processBranchNameResponse.version}'"
      DeployRequest deployRequest = new DeployRequest(conf, processBranchNameResponse.version, processBranchNameResponse.deployEnv)
      deploy(deployRequest)
    }
  }
}


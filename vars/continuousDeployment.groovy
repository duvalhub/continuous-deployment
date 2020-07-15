import com.duvalhub.build.BuildRequest
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.deploy.DeployRequest

def call() {
  initializeSharedLibrary.findSharedLibraryVersion()
  /*
  node('master') {
    def branch = sh(script: "env | grep 'library.shared-library.version' | cut -d '=' -f 2", returnStdout: true).trim()
    env.PIPELINE_BRANCH = branch
  }
   */
  dockerSlave() {
    echo "ze branch= : '${env.PIPELINE_BRANCH}'"
    initializeWorkdir.stage()

    AppConfig conf = readConfiguration()

    ProcessBranchNameRequest processBranchNameRequest = new ProcessBranchNameRequest(BRANCH_NAME)
    ProcessBranchNameResponse processBranchNameResponse = processBranchName(processBranchNameRequest)

    if ( processBranchNameResponse.doBuild ) {
      echo "### Building app on version '${processBranchNameResponse.version}'"
      BuildRequest buildRequest = new BuildRequest(conf, processBranchNameResponse.version)
      build(buildRequest)
    }

    if ( processBranchNameResponse.doDeploy ) {
      echo "### Deploying app version '${processBranchNameResponse.version}' in '${processBranchNameResponse.deployEnv}'"
      DeployRequest deployRequest = new DeployRequest(conf, processBranchNameResponse.version, processBranchNameResponse.deployEnv)
      deploy(deployRequest)
    }
  }
}


import com.duvalhub.build.BuildRequest
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.deploy.DeployRequest

def call() {
  node('master') {
    sh "env"
    dir( "${WORKSPACE}@shared-library") {
      sh "env"

      def branch = sh(script: "env | grep 'library.shared-library.version' | cut -d '=' -f 2", returnStdout: true, trim: true).trim

//        def branch = env['library.shared-library.version']
      echo "hello god"
      echo "Branch: '$branch'"
      env.ZE_BRANCH = branch
    }
  }
  dockerSlave() {
    echo "ze branch= : '${env.ZE_BRANCH}'"
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


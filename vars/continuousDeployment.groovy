import com.duvalhub.build.BuildRequest
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.deploy.DeployRequest

def call() {
  dockerSlave() {

    withSshKey2() {
      sh "docker context create toto-africa --description 'dude' --docker 'host=ssh://root@totoafrica.vps287088.duvalhub.com'"
      sh "docker context use toto-africa"
      sh "docker ps"
    }
//    withCredentials([sshUserPrivateKey(credentialsId: "SERVICE_ACCOUNT_SSH_2", keyFileVariable: 'keyfile')]) {
//
//    }
//    setDockerEnvironment() {
//      echo "allo"
//    }

    sh "exit"

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


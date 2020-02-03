import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.appconfig.AppConfig

def call(ProcessBranchNameRequest request) {
  ProcessBranchNameResponse response = new ProcessBranchNameResponse()
  String branchName = request.branchName
  def releasePattern = /release\/(v.*)/

  switch(branchName) {
      case "develop":
      case ~/test.*/
          response.doBuild = true
          response.version = "latest"
          response.doDeploy = true
          response.deployEnv = "dev"
          break
  
      case ~releasePattern:
          def matcher = branchName =~ releasePattern
          def version = matcher[0][1]
          response.doBuild = true
          response.version = version
          response.doDeploy = true
          response.deployEnv = "stage"
          break
  
      case "master":
          response.doDeploy = true
          dir( env.APP_WORKDIR ) {
            sh "git fetch --tags > /dev/null"
            response.version = sh (returnStdout: true, script: '''
                echo $(git tag --points-at HEAD)
                ''').trim()
          }
          response.deployEnv = "prod"
          break
      default:
        response.doBuild = true
        response.version = "latest"
        response.doDeploy = false
  }

  if ( response.doBuild ) {
      echo "Building version '${response.version}' from branch '${branchName}'"
  }
 
  if ( response.doDeploy ) {
      echo "Promoting version '${response.version}' in '${response.deployEnv}'"
  }
  
  return response
}


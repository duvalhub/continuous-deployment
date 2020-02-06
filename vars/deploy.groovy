import com.duvalhub.deploy.DeployRequest
import com.duvalhub.writecompose.WriteComposeRequest
import com.duvalhub.appconfig.DockerHost
import com.duvalhub.appconfig.Platform

def call(DeployRequest request) {
  stage('Deploy') {
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(request)
    String composeFilePath = writeCompose(writeComposeRequest)
    Platform platform = request.getDockerHost()
    setDockerEnvironment(dockerHost) {
      sh "docker stack deploy -c ${composeFilePath} ${request.getStackName()}"
    }
  }
}


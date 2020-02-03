import com.duvalhub.DeployRequest
import com.duvalhub.WriteComposeRequest
import com.duvalhub.appconfig.DockerHost

def call(DeployRequest request) {
  stage('Deploy') {
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(request)
    String composeFilePath = writeCompose(writeComposeRequest)
    sh "cat ${composeFilePath}"
    DockerHost dockerHost = request.getDockerHost()
    setDockerEnvironment(dockerHost) {
      sh "docker stack deploy -c ${composeFilePath} ${request.getStackName()}"
    }
  }
}


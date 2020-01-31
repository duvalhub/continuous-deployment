import com.duvalhub.DeployRequest
import com.duvalhub.WriteComposeRequest

def call(DeployRequest request) {
  stage('Deploy') {
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(request)
    String composeFilePath = writeCompose(writeComposeRequest)
    sh "cat ${composeFilePath}"
    setDockerEnvironment(request) {
      sh "docker stack deploy -c ${composeFilePath} ${request.getStackName()}"
    }
  }
}


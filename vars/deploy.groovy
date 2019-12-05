import com.duvalhub.DeployRequest
import com.duvalhub.WriteComposeRequest

def call(DeployRequest request) {
  stage('Deploy') {
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(request.appConfig, request.version)
    String composeFilePath = writeCompose(writeComposeRequest)

    sh "cat ${composeFilePath}"
    sh "docker stack deploy -c ${composeFilePath} ${request.getStackName()}"

  }
}


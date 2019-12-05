import com.duvalhub.DeployRequest
import com.duvalhub.AppConfig
import com.duvalhub.WriteComposeRequest

def call(DeployRequest request) {
  stage('Deploy') {
    AppConfig appConfig = request.appConfig
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(allConfig, version)
    String composeFile = writeCompose(writeComposeRequest)

    sh "cat ${composeFile}"
    sh "docker stack deploy -c ${composeFile} ${request.getStackName()}"

  }
}


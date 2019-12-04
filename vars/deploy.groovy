import com.duvalhub.DeployRequest
import com.duvalhub.AppConfig
import com.duvalhub.WriteComposeRequest

def call(DeployRequest request) {
  stage('Deploy') {
    AppConfig appConfig = request.appConfig
    String appName = request.appName
    String version = request.version
    def image = appConfig.getDockerImage(version)
    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(appName, image)
    String composeFile = writeCompose(writeComposeRequest)

    sh "cat ${composeFile}"
    sh "docker stack deploy -c ${composeFile} toto-stack"

  }
}


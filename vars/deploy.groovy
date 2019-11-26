import com.duvalhub.DeployRequest
import com.duvalhub.AppConfig
import com.duvalhub.WriteComposeRequest

def call(DeployRequest request) {
  stage('Build And Push to remote') {
    AppConfig appConfig = request.appConfig
    String appName = request.appName
    //String image = request.image
    String version = request.version

    WriteComposeRequest writeComposeRequest = new WriteComposeRequest(appName, version)
    String composeFile = writeCompose(writeComposeRequest)

    sh "docker stack deploy -c composeFile toto-stack"

  }
}


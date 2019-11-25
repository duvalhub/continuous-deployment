import com.duvalhub.DeployRequest
import com.duvalhub.AppConfig

def call(DeployRequest request) {
  stage('Build And Push to remote') {
    AppConfig appConfig = request.appConfig
    String appName = request.appName
    //String image = request.image
    String version = request.version
    String compose = "docker-compose.yml"

    def processScript = "${env.PIPELINE_WORKDIR}/scripts/bash/processYml.sh"

    env.APP_NAME = appName
    env.IMAGE = appConfig.getDockerImage(version)
    sh "chmod +x ${processScript} && ${processScript} $compose"

  }
}


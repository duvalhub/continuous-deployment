import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

def call(BuildRequest buildRequest) {
  stage('Build And Push to remote') {
    AppConfig conf = buildRequest.appConfig
    def appTye = conf.app.type
    String version = buildRequest.version
    String image = conf.getDockerImage()
    

    env.IMAGE = "${image}:${version}"

    def basePath = "${env.PIPELINE_WORKDIR}"

    env.TEMPLATE_PATH = "${basePath}/build/templates/${appType}"
    env.DOCKERFILE_PATH = "${env.TEMPLATE_PATH}/Dockerfile"

    def script = "${basePath}/scripts/bash/build.sh"

    def appBasePath =  "${env.APP_WORKDIR}/app"
    dir(appBasePath) {
      sh "chmod +x ${script} && ${script}"
    }

  }
}


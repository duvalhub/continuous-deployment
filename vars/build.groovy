import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

def call(BuildRequest buildRequest) {
  stage('Build') {
    AppConfig conf = buildRequest.appConfig
    String version = buildRequest.version
    String image = conf.getDockerImage()
    

    env.IMAGE = "${image}:${version}"

    def basePath = "${env.PIPELINE_WORKDIR}"

    env.TEMPLATE_PATH = "${basePath}/build/templates"
    env.DOCKERFILE_PATH = "${env.TEMPLATE_PATH}/Dockerfile"

    def script = "${basePath}/scripts/bash/build.sh"

    def appBasePath =  "${env.APP_WORKDIR}/app"
    dir(appBasePath) {
      sh "chmod +x ${script} && bash -c \"${script} --templates $TEMPLATE_PATH --builder ${conf.build.builder} --build_destination ${conf.build.destination} --container ${conf.build.container}\""
    }

  }
}


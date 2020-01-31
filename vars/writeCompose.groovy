import com.duvalhub.WriteComposeRequest
import com.duvalhub.appconfig.AppConfig

def call(WriteComposeRequest request) {
  env.APP_NAME = request.appName
  env.IMAGE = request.getImage()
  env.HOSTS = request.getDomainNames()
  if (request.port) {
    env.PORT = request.port
  }
  def processScript = "${env.PIPELINE_WORKDIR}/${request.scriptPath}"
  def compose = request.compose
  sh "chmod +x ${processScript} && ${processScript} ${compose}"
  return compose
}


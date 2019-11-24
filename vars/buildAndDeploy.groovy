import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

def call() {
  dockerSlave() {
    initializeWorkdirStage()
    AppConfig conf = readConfiguration()

    BuildRequest buildRequest = new BuildRequest()
    buildRequest.appConfig = conf
    build(buildRequest)
  }
}


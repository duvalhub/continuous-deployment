import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

def call(BuildRequest buildRequest) {
  stage('Build And Push to remote') {
    AppConfig conf = buildRequest.appConfig
  }
}


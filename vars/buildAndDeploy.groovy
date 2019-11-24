import com.duvalhub.BuildAndPushRequest
import com.duvalhub.AppConfig

def call() {
  dockerSlave() {
    initializeWorkdirStage()
    AppConfig conf = readConfiguration()

//    BuildAndPushRequest buildAndPushRequest = new BuildAndPushRequest()
//    buildAndPush()

    echo "${conf}"
    sh "ls -l $APP_WORKDIR"
    sh "ls -l $PIPELINE_WORKDIR"
  }
}


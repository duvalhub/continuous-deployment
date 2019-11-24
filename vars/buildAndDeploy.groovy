import com.duvalhub.BuildAndPushRequest

def call() {
  dockerSlave() {
    initializeWorkdirStage()
    conf = readConfiguration()

    BuildAndPushRequest buildAndPushRequest = new BuildAndPushRequest()
    buildAndPush()

    echo "${conf}"
    sh "ls -l $APP_WORKDIR"
    sh "ls -l $PIPELINE_WORKDIR"
  }
}


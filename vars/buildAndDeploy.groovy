
def call() {
  dockerSlave() {
    initializeWorkdirStage()
    conf = readConfiguration()

    echo "${conf}"
    sh "ls -l $APP_WORKDIR"
    sh "ls -l $PIPELINE_WORKDIR"
  }
}


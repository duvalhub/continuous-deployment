
def call() {
  dockerSlave() {
    initializeWorkdirStage()
    conf = readConfiguration()

    echo "${conf}"
  }
}


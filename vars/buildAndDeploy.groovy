
def call() {
  dockerSlave() {
    checkout scm 
    initializeWorkdirStage()
    conf = readConfiguration()

    echo "${conf}"
  }
}



def call() {
  node {
    echo "Hello from pipeline"

    initializeWorkdirStage()

    conf = readConfiguration()

    echo "${conf}"

  }

}


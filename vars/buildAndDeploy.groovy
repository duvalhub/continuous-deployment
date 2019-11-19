
def call() {

  docker.withServer('tcp://docker-dev.philippeduval.ca:2376', env.DOCKER_BUNDLE_ID) {
      docker.image('alpine/git').inside() {
          /* do things */
          echo "hello"
          sh "git --version"
      }
  }

  node {
    echo "Hello from pipeline"

    initializeWorkdirStage()

    conf = readConfiguration()

    echo "${conf}"

  }

}


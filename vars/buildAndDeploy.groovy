
def call() {

//node {
//  echo "hello form container"
//  sh "git --version"
//}
dockerSlave() {
  echo "hello"
  sh "ls -l"
  sh "pwd"
  sh "git --version"
  echo "Hello from pipeline"

  sh "env"
  sh "env | grep GIT"
  echo "$GIT_URL"
  echo "$BRANCH_NAME"
  return
    initializeWorkdirStage()

    conf = readConfiguration()

    echo "${conf}"
}
/*
  docker.withServer('tcp://docker-dev.philippeduval.ca:2376', env.DOCKER_BUNDLE_ID) {
      docker.image('alpine/git').inside() {
          echo "hello"
          sh "ls -l"
          sh "pwd"
          sh "git --version"
      }
  }
  */
//  node {
//    echo "Hello from pipeline"
//
//    initializeWorkdirStage()
//
//    conf = readConfiguration()
//
//    echo "${conf}"
//
//  }

}


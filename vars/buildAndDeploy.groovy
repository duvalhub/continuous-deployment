
def call() {

//node {
//  echo "hello form container"
//  sh "git --version"
//}
dockerNode {
  echo "hello"
  sh "ls -l"
  sh "pwd"
  sh "git --version"
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


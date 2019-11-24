
def call() {

//node {
//  echo "hello form container"
//  sh "git --version"
//}
dockerSlave() {
  echo "hello"
  sh "ls -all"
  sh "pwd"
  return
  sh "git --version"
  echo "Hello from pipeline"

  sh "env"
  echo "+++++++++++++++++++++++++++++++++yy"
  sh "git remote -v"
  echo "+++++++++++++++++++++++++++++++++yy"
  def gitUrl = sh returnStdout: true, script: 'git config --get remote.origin.url'
  echo gitUrl
  echo "+++++++++++++++++++++++++++++++++yy"
  echo "$BRANCH_NAME"
  echo "+++++++++++++++++++++++++++++++++yy"
  echo "$GIT_URL"
    initializeWorkdirStage()

  return
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


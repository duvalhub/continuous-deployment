import com.duvalhub.GitCloneRequest

def call() {

  node {
    echo "Hello from pipeline"

    stage('Initialization') {
      GitCloneRequest request = new GitCloneRequest("https://github.com/duvalhub/helloworld-app.git", "hello-world")
      gitClone(request)

      sh "ls -l"
    }

  }

}


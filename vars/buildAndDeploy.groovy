import com.duvalhub.GitCloneRequest

def call() {
  echo "Hello from pipeline"

  GitCloneRequest request = new GitCloneRequest("git@github.com:duvalhub/helloworld-app.git", "hello-world")
  gitClone(request)

  sh "ls -l"

}


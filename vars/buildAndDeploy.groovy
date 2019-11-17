import com.duvalhub.GitCloneRequest

def call() {

  node {
    echo "Hello from pipeline"

    initializeWorkdirStage()

    conf = readConfiguration()

    echo conf

  }

}


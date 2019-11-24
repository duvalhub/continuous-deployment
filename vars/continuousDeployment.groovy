import com.duvalhub.BuildRequest
import com.duvalhub.AppConfig

def call() {
  dockerSlave() {
    initializeWorkdirStage()
    AppConfig conf = readConfiguration()

    ProcessBranchNameRequest processBranchNameRequest = new ProcessBranchNameRequest(BRANCH_NAME)
    ProcessBranchNameResponse processBranchNameResponse = processBranchName(processBranchNameRequest)

    echo processBranchNameResponse

//    BuildRequest buildRequest = new BuildRequest()
//    buildRequest.appConfig = conf
//    build(buildRequest)
  }
}


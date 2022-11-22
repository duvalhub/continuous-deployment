import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.processbranchname.ProcessBranchNameResponse

def call(ProcessBranchNameRequest request) {
    ProcessBranchNameResponse response = new ProcessBranchNameResponse()
    String branchName = request.branchName
    def releasePattern = /release\/(.*)/

    switch (branchName) {
        case ~releasePattern:
            def matcher = branchName =~ releasePattern
            def version = matcher[0][1]
            response.doBuild = true
            response.version = version
            response.doDeploy = true
            response.deployEnv = "stage"
            break
        case "master":
        case "production":
            response.doDeploy = true
            dir(env.APP_WORKDIR) {
                withSshKey("github.com", "SERVICE_ACCOUNT_SSH", "git") {
//                    sh "cat ~/.ssh/config"
//                    sh 'echo $SSH_HOST'
//                    sh "git branch"
//                    sh "git status"
                    sh "git remote -v"
                    sh "git remote set-url origin git@${SSH_HOST}:"
//                    sh "git pull"
                    sh "git fetch --tags > /dev/null"
                    response.version = sh(returnStdout: true, script: '''
                        git tag --points-at HEAD
                    ''').trim()
                }
            }
            response.deployEnv = "prod"
            break;
        case "main":
        case "develop":
            dir(env.APP_WORKDIR) {
                String buildNumber = sh(returnStdout: true, script: '''
                    git rev-list --count HEAD
                ''').trim()
                String shortCommit = sh(returnStdout: true, script: '''
                    git rev-parse --short HEAD
                ''').trim()
                response.version = String.format("%s-%s", buildNumber, shortCommit)
            }
            response.doBuild = true
            response.doDeploy = true
            response.deployEnv = "dev"
            break
        default:
            response.doBuild = true
            response.version = "latest"
            response.doDeploy = true
            response.deployEnv = "dev"
            break
    }


    if (response.doBuild) {
        echo "Building version '${response.version}' from branch '${branchName}'"
    } else {
        echo "We don't build this branch: '${branchName}'"
    }

    if (response.doDeploy) {
        echo "Promoting version '${response.version}' in '${response.deployEnv}'"
    } else {
        echo "We don't deploy this branch: '${branchName}'"
    }

    return response
}


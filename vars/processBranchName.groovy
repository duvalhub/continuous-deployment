import com.duvalhub.appconfig.StrategyType
import com.duvalhub.processbranchname.ProcessBranchNameRequest
import com.duvalhub.initializeworkdir.SharedLibrary
import com.duvalhub.processbranchname.ProcessBranchNameResponse
import com.duvalhub.appconfig.AppConfig
import com.sun.org.apache.xerces.internal.parsers.CachingParserPool

def call(ProcessBranchNameRequest request, AppConfig appConfig) {
    ProcessBranchNameResponse response = new ProcessBranchNameResponse()
    String branchName = request.branchName
    StrategyType type = appConfig.strategy.type
    switch (type) {
        case StrategyType.MULTI_BRANCH:
            setAsMultiBranch(request, response)
            break;
        case StrategyType.ONE_BRANCH:
            setAsOneBranch(request, response)
            break;
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


def setAsMultiBranch(ProcessBranchNameRequest request, ProcessBranchNameResponse response) {
    String branchName = request.branchName
    echo "Applyting strategy MultiBranch on ${branchName}"
    def releasePattern = /release\/(.*)/
    String appVersion = getVersionSignature(env.APP_WORKDIR);
    String libVersion = getVersionSignature(SharedLibrary.getWorkdir(env));
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
            response.version = getTag(env.APP_WORKDIR)
            response.deployEnv = "prod"
            break;
        case "main":
        case "develop":
            response.version = buildVersionTag(appVersion, libVersion)
            response.doBuild = true
            response.doDeploy = true
            response.deployEnv = "dev"
            break
        default:
            response.doBuild = true
            response.version = buildVersionTag(appVersion, libVersion, branchName)
            response.doDeploy = true
            response.deployEnv = "dev"
            break
    }
}

def setAsOneBranch(ProcessBranchNameRequest request, ProcessBranchNameResponse response) {
    String branchName = request.branchName
    echo "Applyting strategy OneBranch on ${branchName}"
    String appVersion = getVersionSignature(env.APP_WORKDIR);
    String libVersion = getVersionSignature(SharedLibrary.getWorkdir(env));
    switch (branchName) {
        case "main":
        case "master":
            response.version = buildVersionTag(appVersion, libVersion)
            response.doBuild = true
            response.doDeploy = true
            response.deployEnv = "prod"
            break
        default:
            response.doBuild = true
            response.version = buildVersionTag(appVersion, libVersion, branchName)
            response.doDeploy = true
            response.deployEnv = "dev"
            break
    }
}

static String buildVersionTag(String appVersion, String libVersion) {
    return String.format("%s-%s", appVersion, libVersion)
}

static String buildVersionTag(String appVersion, String libVersion, String branchName) {
    return sanitize(String.format("%s-%s", branchName, buildVersionTag(appVersion, libVersion)))
}

static String sanitize(String tag) {
    tag = tag.replaceAll("/", "-")
    if (tag.length() > 128) {
        tag = tag.substring(tag.length() - 128, tag.length())
    }
    return tag
}

String getVersionSignature(path) {
    dir(path) {
        String buildNumber = sh(returnStdout: true, script: '''
                    git rev-list --count HEAD
                ''').trim()
        String shortCommit = sh(returnStdout: true, script: '''
                    git rev-parse --short HEAD
                ''').trim()
        return String.format("%s.%s", buildNumber, shortCommit)
    }
}

String getTag(String path) {
    dir(path) {
        withSshKey("github.com", "SERVICE_ACCOUNT_SSH", "git") {
            sh '''#!/usr/bin/env bash
                        origin_url=$(git remote get-url origin)
                        echo "URL = $origin_url"
                        IFS='/' read -ra URL_PARTS <<<"$origin_url"
                        echo "Parts are ${URL_PARTS[@]}"
                        git remote set-url origin git@${SSH_HOST}:${URL_PARTS[3]}/${URL_PARTS[4]}
                        git fetch --tags > /dev/null
                    '''
            return sh(returnStdout: true, script: '''
                        git tag --points-at HEAD
                    ''').trim()
        }
    }
}
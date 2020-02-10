package com.duvalhub.git

import com.duvalhub.BaseObject
import com.duvalhub.git.GitRepo

class GitCloneRequest extends BaseObject {
    String directory
    GitRepo gitRepo
    String credentialsId = "SERVICE_ACCOUNT_SSH"

    GitCloneRequest(GitRepo gitRepo, String toCheckout) {
        this(gitRepo)
        this.toCheckout = toCheckout
    }

    GitCloneRequest(GitRepo gitRepo ) {
        this.gitRepo = gitRepo
        this.directory = "cloned"
    }
}
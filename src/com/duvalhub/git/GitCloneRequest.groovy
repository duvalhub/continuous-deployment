package com.duvalhub.git

import com.duvalhub.BaseObject

class GitCloneRequest extends BaseObject {
    String directory
    String url
    String toCheckout
    String credentialsId = "SERVICE_ACCOUNT_SSH"

    GitCloneRequest(String url, String directory, String toCheckout) {
        this(url, directory)
        this.toCheckout = toCheckout
    }

    GitCloneRequest(String url, String directory) {
        this(url)
        this.directory = directory
    }

    GitCloneRequest(String url ) {
        this.url = url
        this.directory = "cloned"
    }
}
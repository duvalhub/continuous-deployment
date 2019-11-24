package com.duvalhub

class GitCloneRequest {
    String directory
    String url
    String toCheckout

    GitCloneRequest(String url, String directory, String toCheckout) {
        this.url = url
        this.directory = directory
        this.toCheckout = toCheckout
    }

    GitCloneRequest(String url, String directory) {
        this.url = url
        this.directory = directory
    }

    GitCloneRequest(String url ) {
        this.url = url
        this.directory = "cloned"
    }
}
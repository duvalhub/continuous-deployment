package com.duvalhub

class AppConfig {
    String directory
    String url
    String toCheckout

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
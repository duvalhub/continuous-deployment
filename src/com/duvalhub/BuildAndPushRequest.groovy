package com.duvalhub

import com.duvalhub.AppConfig

class BuildAndPushRequest {
    AppConfig appConfig
    String directory
    String url
    String toCheckout

    BuildAndPushRequest(String url, String directory, String toCheckout) {
        this(url, directory)
        this.toCheckout = toCheckout
    }

    BuildAndPushRequest(String url, String directory) {
        this(url)
        this.directory = directory
    }

    BuildAndPushRequest(String url ) {
        this.url = url
        this.directory = "cloned"
    }
}
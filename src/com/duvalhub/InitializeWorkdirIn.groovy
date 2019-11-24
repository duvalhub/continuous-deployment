package com.duvalhub

class InitializeWorkdirIn {
    String appWorkdir
    String appGitUrl
    String appToCheckout
    String pipelineWorkdir
    String pipelineGitUrl
    String pipelineToCheckout

    InitializeWorkdirIn() {
        this.pipelineGitUrl = "https://github.com/duvalhub/continuous-deployment.git"
        this.pipelineWorkdir = "jenkins-workdir"
        this.appWorkdir = "app-workdir"
    }

    InitializeWorkdirIn(String url ) {
        this.url = url
        this.directory = "cloned"
    }
}
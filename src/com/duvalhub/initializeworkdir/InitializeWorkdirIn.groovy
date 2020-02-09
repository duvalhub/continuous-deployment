package com.duvalhub.initializeworkdir

import com.duvalhub.BaseObject

class InitializeWorkdirIn extends BaseObject {
    String appWorkdir
    String appGitUrl
    String appToCheckout
    String pipelineWorkdir
    String pipelineGitUrl
    String pipelineToCheckout

    InitializeWorkdirIn() {
        this.pipelineGitUrl = "https://github.com/duvalhub/continous-deployment.git"
        this.pipelineWorkdir = "jenkins-workdir"
        this.appWorkdir = "app-workdir"
    }

    InitializeWorkdirIn(String url ) {
        this()
        this.url = url
    }
}
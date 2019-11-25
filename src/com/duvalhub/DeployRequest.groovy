package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig

class DeployRequest extends BaseObject {
    AppConfig appConfig
    String appName
    String image

    DeployRequest(AppConfig appConfig, String version) {
        println "we are hrer"
        this.appName = appConfig.app.name
        this.appConfig = appConfig
        //this.image = appConfig.getDockerImage(version)
    }
}

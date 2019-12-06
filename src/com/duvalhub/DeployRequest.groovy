package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig

class DeployRequest extends BaseObject {
    AppConfig appConfig
    String appName
    String image
    String version
    String environment

    DeployRequest(AppConfig appConfig, String version, String environment) {
        this.appConfig = appConfig
        this.appName = appConfig.app.name
        this.version = version
        this.environment = environment
    }

    String getDockerImage() {
        return "${appConfig.getDockerImage()}:${this.version}"
    }

    String getStackName(){
        return "${this.appConfig.app.group}-${this.environment}"
    }
}

package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig

class DeployRequest extends BaseObject {
    String appName
    String image

    DeployRequest(AppConfig appConfig, String version) {
        this.appName = appConfig.app.name
        this.image = appConfig.getDockerImage(version)
    }
}

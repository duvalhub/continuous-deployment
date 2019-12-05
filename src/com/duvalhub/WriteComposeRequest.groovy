package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    DeployRequest request
    String scriptPath = "scripts/bash/processYml.sh"
    String compose = "docker-compose.yml"
    String appName
    String image
    String hosts

    WriteComposeRequest(DeployRequest request) {
        this.request = request
        AppConfig config = request.appConfig
        this.appName = config.app.name
        this.hosts = config.deploy.hosts
    }

    String getImage() {
        return request.getDockerImage()
    }

}

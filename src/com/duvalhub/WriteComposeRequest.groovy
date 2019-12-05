package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    String scriptPath = "scripts/bash/processYml.sh"
    String compose = "docker-compose.yml"
    String appName
    String image
    String hosts

    WriteComposeRequest(DeployRequest request, AppConfig config) {
        this.appName = config.app.name
        String version = request.version
        this.image = config.getDockerImage(request.version)
        this.hosts = config.deploy.hosts
    }

}

package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    DeployRequest request
    String base = "philippeduval.ca"
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

    String getHosts() {
        String urls = "${this.appName}.${this.request.appConfig.app.group}.${this.request.environment}.${this.base}"

        if(this.hosts) {
            urls += ",${this.hosts}"
        }
        return urls
    }

}

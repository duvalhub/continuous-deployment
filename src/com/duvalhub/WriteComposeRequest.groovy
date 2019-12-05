package com.duvalhub

import com.duvalhub.BaseObject
import com.duvalhub.AppConfig
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    DeployRequest request
    AppConfig config
    String base = "philippeduval.ca"
    String scriptPath = "scripts/bash/processYml.sh"
    String compose = "docker-compose.yml"
    String appName
    String image
    String hosts

    WriteComposeRequest(DeployRequest request) {
        this.request = request
        this.config = request.appConfig
        this.appName = this.config.app.name
        this.hosts = this.config.deploy.hosts
    }

    String getImage() {
        return request.getDockerImage()
    }

    String getHosts() {
        return "hellow-wrol.pd.ca"
        String urls = "${this.appName}.${this.config.app.group}.${this.request.environment}.${this.base}"

        if(this.hosts) {
            urls += ",${this.hosts}"
        }
        return urls
    }

}

package com.duvalhub.writecompose

import com.duvalhub.BaseObject
import com.duvalhub.appconfig.AppConfig
import groovy.json.JsonBuilder
import com.duvalhub.deploy.DeployRequest

class WriteComposeRequest extends BaseObject {
    DeployRequest request
    AppConfig config
    String base = "philippeduval.ca"
    String scriptPath = "scripts/bash/processYml/processYml.sh"
    String compose = "docker-compose.yml"
    String stackName
    String appName
    String image
    String hosts
    String port

    WriteComposeRequest(DeployRequest request) {
        this.request = request
        this.config = request.appConfig
        this.stackName = request.getStackName()
        this.appName = this.config.app.name
        if (this.config.deploy) {
            if( this.config.deploy.hosts )  {
                this.hosts = this.config.deploy.hosts
            }
            if ( this.config.deploy.port ) {
                this.port = this.config.deploy.port
            }
        }
    }

    String getImage() {
        return this.request.getDockerImage()
    }

    String getDomainNames() {
        def name = this.appName
        def group = this.config.app.group
        def env = this.request.environment
        def base = this.base
        def urls = [this.appName, this.config.app.group, this.request.environment, this.base].join(".")
        if(this.hosts) {
           urls += this.hosts
        }
        return urls        
    }
}

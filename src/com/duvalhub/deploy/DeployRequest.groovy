package com.duvalhub.deploy

import com.duvalhub.BaseObject
import com.duvalhub.appconfig.AppConfig
import com.duvalhub.appconfig.DockerHost

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

    DockerHost getDockerHost() {
        DockerHost host
        switch(this.environment) {
            case "dev":
            case "stage":
                host = this.appConfig.docker.hosts.dev
                break
            case "prod":
                host = this.appConfig.docker.hosts.prod
                break
            default:
                throw new Exception("Environment can be mapped: '${this.environment}'")
        }
        return host
    }

    String getDockerUrl() {
        return this.getDockerHost().getDockerUrl()
    }

    String getBundleId() {
        return this.getDockerHost().bundleId
    }

    String getCredentialId() {
        return this.getDockerHost().credentialId
    }

    String getDockerImage() {
        return "${appConfig.getDockerImage()}:${this.version}"
    }

    String getStackName(){
        return "${this.appConfig.app.group}-${this.environment}"
    }
}

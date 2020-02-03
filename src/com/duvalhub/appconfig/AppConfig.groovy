package com.duvalhub.appconfig

import com.duvalhub.BaseObject

class AppConfig extends BaseObject {
    App app
    Docker docker
    Build build
    Deploy deploy

    String getDockerImage(){
        return "${this.docker.registry}/${this.docker.namespace}/${this.docker.repository}"
    }
}
class App {
    String name
    String group
}
class Build {
    String builder
    String destination = "build"
    String container
}
class Deploy {
    String hosts
    String port = "80"
}
class Docker {
    String registry
    String namespace
    String repository
    DockerHosts hosts
}

class DockerHosts {
    DockerHost dev
    DockerHost prod
}

class DockerHost extends BaseObject {
    String protocole = "tcp"
    String url
    String port = "2376"
    String credentialId
    String bundleId

    String getDockerUrl() {
        return String.format("%s://%s:%s", this.protocole, this.url, this.port)
        
    }
}
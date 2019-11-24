package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class AppConfig extends BaseObject {
    App app
    Docker docker

    String getDockerImage(){
        return "${this.docker.registry}/${this.docker.namespace}/${this.docker.repository}"
    }
}

class App {
    String name
    String type
    String gros
}

class Docker {
    String registry
    String namespace
    String repository
}
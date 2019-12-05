package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class AppConfig extends BaseObject {
    App app
    Docker docker
    Epais unpais
    Build build
    Deploy deploy

    String getDockerImage(){
        return "${this.docker.registry}/${this.docker.namespace}/${this.docker.repository}"
    }
     String getDockerImage(String version){
        return "${this.getDockerImage()}:${version}"
    }
}
class Epais {
    String unpais
}
class Build {
    String builder
    String container
}
class Deploy {
    String hosts
}
class App {
    String name
    String type
    String gros = "whatup"
}

class Docker {
    String registry
    String namespace
    String repository
}
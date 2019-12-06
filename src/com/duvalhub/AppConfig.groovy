package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

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
    String container
}
class Deploy {
    String hosts
}
class Docker {
    String registry
    String namespace
    String repository
}
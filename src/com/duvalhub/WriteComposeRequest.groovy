package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    String scriptPath = "scrips/bash/processYml.sh"
    String compose = "docker-compose.yml"
    String appName
    String version

    WriteComposeRequest(String appName, String version) {
        this.appName = appName
        this.version = version
    }

}

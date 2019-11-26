package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class WriteComposeRequest extends BaseObject {
    String scriptPath = "scrips/bash/processYml.sh"
    String compose = "docker-compose.yml"
    String appName
    String image

    WriteComposeRequest(String appName, String image) {
        this.appName = appName
        this.image = image
    }

}

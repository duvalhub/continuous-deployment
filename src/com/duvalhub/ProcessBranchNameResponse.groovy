package com.duvalhub

import groovy.json.JsonBuilder

class ProcessBranchNameResponse {
    Boolean doBuild 
    String version
    Boolean doDeploy
    String deployEnv

    ProcessBranchNameResponse() {
        this.doBuild = false
        this.doDeploy = false
    }

    String toString() {
        return new JsonBuilder( this ).toPrettyString()
    }

}

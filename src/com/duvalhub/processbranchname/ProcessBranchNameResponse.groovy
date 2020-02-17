package com.duvalhub.processbranchname

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class ProcessBranchNameResponse extends BaseObject {
    Boolean doBuild 
    String version
    Boolean doDeploy
    String deployEnv

    ProcessBranchNameResponse() {
        this.doBuild = false
        this.doDeploy = false
    }

}

package com.duvalhub

import com.duvalhub.BaseObject
import groovy.json.JsonBuilder

class ProcessBranchNameRequest extends BaseObject {
    String branchName

    ProcessBranchNameRequest(String branchName) {
        this.branchName = branchName
    }

}

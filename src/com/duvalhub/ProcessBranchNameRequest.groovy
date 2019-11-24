package com.duvalhub

import groovy.json.JsonBuilder

class ProcessBranchNameRequest {
    String branchName

    ProcessBranchNameRequest(String branchName) {
        this.branchName = branchName
    }

    String toString() {
        return new JsonBuilder( this ).toPrettyString()
    }

}

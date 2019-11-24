package com.duvalhub

import groovy.json.JsonBuilder

class AppConfig {
    App app

    String toString() {
        return new JsonBuilder( this ).toPrettyString()
    }

}

class App {
    String name
    String type
    String gros
}
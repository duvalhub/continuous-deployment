def call(String script_path) {
    return sh(returnStdout: true, script: "chmod +x ${script_path} && ${script_path}").trim()
}
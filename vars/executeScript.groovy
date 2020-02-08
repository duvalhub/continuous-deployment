def call(String script_path) {
    sh "chmod +x ${script_path} && ${script_path}"
}
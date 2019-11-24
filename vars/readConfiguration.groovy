def call() {
    def workdir = "${env.APP_WORKDIR}"
    dir(workdir) {
        conf = readYaml file:'continuous-deployment/config.yml'
        return conf
    }
}
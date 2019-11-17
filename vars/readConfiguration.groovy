def call() {
    echo "Hello"
    echo "${env.APP_WORKDIR}"
    sh "ls -l ${env.APP_WORKDIR}"
    def workdir = "${env.APP_WORKDIR}"
    dir(workdir) {
        echo "Hello wtf"
        conf = readYaml file:'continuous-deployment/config.yml'
        return conf
    }
}
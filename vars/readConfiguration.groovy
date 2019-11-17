def call() {
    echo "Hello"
    echo "${env.APP_WORKDIR}"
    sh "ls -l ${env.APP_WORKDIR}"
    dir(env.APP_WORDIR) {
        echo "Hello wtf"
        conf = readYml 'continuous-deployment/config.yml'
        return conf
    }
}
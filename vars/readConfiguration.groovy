def call() {
    echo "Hello"
    echo "${env.APP_WORKDIR}"
    dir(env.APP_WORDIR) {
        conf = readYml 'continuous-deployment/config.yml'
        return conf
    }
}
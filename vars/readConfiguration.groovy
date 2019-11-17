def call() {
    dir(env.APP_WORDIR) {
        conf = readYml 'continuous-deployment/config.yml'
        return conf
    }
}
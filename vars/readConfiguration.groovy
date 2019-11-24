import com.duvalhub.AppConfig

def call() {
    def workdir = "${env.APP_WORKDIR}"
    dir(workdir) {
        AppConfig conf = readYaml file:'continuous-deployment/config.yml'
        echo "${conf.getClass()}"
        echo "${conf.app.name}"
        return conf
    }
}
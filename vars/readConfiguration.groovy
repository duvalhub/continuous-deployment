import com.duvalhub.appconfig.AppConfig

def call() {
    def workdir = "${env.APP_WORKDIR}"
//    dir(workdir) {
        AppConfig conf = readYaml file:'config.yml'
        echo conf.toString()
        return conf
    //}
}
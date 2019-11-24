def call(Closure body) {
    withCredentials([
        dockerCert(credentialsId: env.DOCKER_BUNDLE_ID, variable: 'DOCKER_CERT_PATH'),
        usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_CREDENTIALS_USR', passwordVariable: 'DOCKER_CREDENTIALS_PSW')
    ]) {
      env.DOCKER_HOST="tcp://docker-dev.philippeduval.ca:2376"
      env.DOCKER_TLS_VERIFY=1
      body()
    }
}
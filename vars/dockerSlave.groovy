def call(Closure body) {
    def dockerSlaveImage = 'huguesmcd/jenkins-slave'
    dockerNode(dockerSlaveImage) { 
        withCredentials([
            dockerCert(credentialsId: env.DOCKER_BUNDLE_ID, variable: 'DOCKER_CERT_PATH'),
            usernamePassword(credentialsId: env.DOCKER_CREDENTIALS_ID, usernameVariable: 'DOCKER_CREDENTIALS_USR', passwordVariable: 'DOCKER_CREDENTIALS_PSW')
        ]) {
          body()
        }
    }
}
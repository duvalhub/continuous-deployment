def call(Closure body) {
    def dockerSlaveImage = 'huguesmcd/jenkins-slave'
    dockerNode(dockerSlaveImage) { 
        withCredentials([dockerCert(credentialsId: env.DOCKER_BUNDLE_ID, variable: 'DOCKER_CERT_PATH')]) {
          body()
        }
    }
}
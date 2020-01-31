import com.duvalhub.appconfig.DockerHost

def call(DockerHost dockerHost, Closure body) {
    withCredentials([
        dockerCert(credentialsId: dockerHost.bundleId, variable: 'DOCKER_CERT_PATH'),
        usernamePassword(credentialsId: dockerHost.credentialId, usernameVariable: 'DOCKER_CREDENTIALS_USR', passwordVariable: 'DOCKER_CREDENTIALS_PSW')
    ]) {
      echo "${env.DOCKER_CREDENTIALS_USR}"
      echo "${env.DOCKER_CREDENTIALS_PSW}"
      env.DOCKER_HOST = dockerHost.getUrl()()
      env.DOCKER_TLS_VERIFY = 1
      body()
    }
}
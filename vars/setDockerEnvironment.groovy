def call(DeployRequest deployRequest, Closure body) {
    withCredentials([
        dockerCert(credentialsId: deployRequest.getBundleId(), variable: 'DOCKER_CERT_PATH'),
        usernamePassword(credentialsId: deployRequest.getCredentialId(), usernameVariable: 'DOCKER_CREDENTIALS_USR', passwordVariable: 'DOCKER_CREDENTIALS_PSW')
    ]) {
      echo "${env.DOCKER_CREDENTIALS_USR}"
      echo "${env.DOCKER_CREDENTIALS_PSW}"
      env.DOCKER_HOST = deployRequest.getDockerUrl()
      env.DOCKER_TLS_VERIFY = 1
      body()
    }
}
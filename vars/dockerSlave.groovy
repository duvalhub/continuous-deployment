def call(Closure body) {
    def dockerSlaveImage = 'duvalhub/jenkins-slave:1.0.3'
    dockerNode(dockerSlaveImage) { 
        setDockerEnvironment() {
          body()
        }
    }
}
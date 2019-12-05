def call(Closure body) {
    def dockerSlaveImage = 'duvalhub/jenkins-slave:1.0.2'
    dockerNode(dockerSlaveImage) { 
        setDockerEnvironment() {
          body()
        }
    }
}
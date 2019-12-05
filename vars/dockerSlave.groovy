def call(Closure body) {
    def dockerSlaveImage = 'huguesmcd/jenkins-slave:1.0.2'
    dockerNode(dockerSlaveImage) { 
        setDockerEnvironment() {
          body()
        }
    }
}
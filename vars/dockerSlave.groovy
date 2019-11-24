def call(Closure body) {
    def dockerSlaveImage = 'huguesmcd/jenkins-slave'
    dockerNode(dockerSlaveImage) { 
        body()
    }
}
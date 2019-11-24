def call(Closure body) {
    def dockerSlaveImage = 'huguesmcd/jenkins-slave:1.0.0'
    dockerNode('jenkins/slave') { 
        body()
    }
}
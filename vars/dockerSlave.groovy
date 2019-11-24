def call(Closure body) {
    //def dockerSlaveImage = 'huguesmcd/jenkins-slave:1.0.0'
    def dockerSlaveImage = 'communitycloud/docker-client'
    dockerNode('jenkins/slave') { 
        body()
    }
}
def call(Closure body) {
    def dockerSlaveImage = 'jenkins/slave'
    dockerNode('jenkins/slave') { 
        body()
    }
}
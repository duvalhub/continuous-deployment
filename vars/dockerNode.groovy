def call() {
    def dockerSlaveImage = 'jenkins/slave'
    dockerNode('jenkins/slave') { 
        body()
    }
}
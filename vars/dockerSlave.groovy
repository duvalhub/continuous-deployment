def call(Closure body) {
    def dockerSlaveImage = 'jenkins/slave'
    sh "git config --get remote.origin.url"
    dockerNode('jenkins/slave') { 
        body()
    }
}
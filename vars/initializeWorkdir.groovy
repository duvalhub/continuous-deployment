def call() {

    GitCloneRequest request = new GitCloneRequest("https://github.com/duvalhub/helloworld-app.git", "hello-world")
    gitClone(request)
    env.APP_WORKDIR = "$PWD/${request.directory}"

}
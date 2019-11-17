class GitCloneRequest {
    String directory
    String url

    GitCloneRequest(String url, String directory) {
        this.url = url
        this.directory = directory
    }
}
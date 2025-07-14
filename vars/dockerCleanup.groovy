def call(String project, String hubUser, String ImageTag = "latest") {
    sh "docker rmi ${hubUser}/${project}:${ImageTag} || true"
    sh "docker rmi ${hubUser}/${project}:latest || true"
}


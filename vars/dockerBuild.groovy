def call(String project, String hubUser, String ImageTag = "latest") {
    sh "docker image build -t ${hubUser}/${project} ."
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}"
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:latest"

    withCredentials([usernamePassword(
    credentialsId: 'docker_cred',
    usernameVariable: 'USER',
    passwordVariable: 'PASS'
)]) {
    sh """
        echo Logging into DockerHub...
        echo \$PASS | docker login -u \$USER --password-stdin
        docker image push ${hubUser}/${project}:${ImageTag}
        docker image push ${hubUser}/${project}:latest
    """
}


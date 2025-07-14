def call(String project, String hubUser, String ImageTag = "latest") {
    sh "docker image build -t ${hubUser}/${project} ."
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}"
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:latest"

    withCredentials([usernamePassword(
        credentialsId: "docker-hub",
        usernameVariable: "sonali1897",
        passwordVariable: "Krishna18@"
    )]) {
        sh "docker login -u '$USER' -p '$PASS'"
    }

    sh "docker image push ${hubUser}/${project}:${ImageTag}"
    sh "docker image push ${hubUser}/${project}:latest"
}

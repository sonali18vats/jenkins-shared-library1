// vars/dockerBuild.groovy

def call(String project, String hubUser, String ImageTag = "latest") {

    sh "docker image build -t ${hubUser}/${project} ."
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:${ImageTag}"
    sh "docker tag ${hubUser}/${project} ${hubUser}/${project}:latest"

    withCredentials([usernamePassword(
        credentialsId: "docker-hub",        // This must match your Jenkins saved credentials ID
        usernameVariable: 'sonali1897',            // These variables are ONLY available inside the block
        passwordVariable: 'PASS'
    )]) {
        sh """
            echo Logging into Docker...
            docker login -u "$USER" -p "$PASS"
            docker image push ${hubUser}/${project}:${ImageTag}
            docker image push ${hubUser}/${project}:latest
        """
    }
}

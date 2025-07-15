def call(Map stageParams = [:]) {
    if (!stageParams.branch || !stageParams.url) {
        error "Missing required parameters: 'branch' and/or 'url'"
    }

    checkout([
        $class: 'GitSCM',
        branches: [[name: stageParams.branch]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[
            url: stageParams.url,
            credentialsId: stageParams.get('credentialsId', '') // Optional
        ]]
    ])
}

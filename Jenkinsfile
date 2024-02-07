node {
    def repourl = "${REGISTRY_URL}/${PROJECT_ID}/${ARTIFACT_REGISTRY}"
    def mvnHome = tool name: 'maven', type: 'maven'
    def mvnCMD = "${mvnHome}/bin/mvn "

    stage('Checkout') {
        checkout([$class: 'GitSCM',
            branches: [[name: '*/master']],
            extensions: [],
            userRemoteConfigs: [[credentialsId: 'git',
            url: 'https://github.com/gee-cpu/config-server.git']]])
    }

    stage('Build and Push Image') {
        withCredentials([file(credentialsId: 'gcp', variable: 'GC_KEY')]) {
            sh("gcloud auth activate-service-account --key-file=${GC_KEY}")
            sh 'gcloud auth configure-docker us-west4-docker.pkg.dev'
            sh "${mvnCMD} clean install jib:build -DREPO_URL=${repourl}"
        }
    }

    stage('Deploy') {
        sh "sed -i 's|IMAGE_URL|${repourl}|g' k8s/deployment.yaml"
        step([$class: 'KubernetesEngineBuilder',
            projectId: env.PROJECT_ID,
            clusterName: env.CLUSTER,
            location: env.ZONE,
            manifestPattern: 'k8s/deployment.yaml',
            credentialsId: env.PROJECT_ID,
            verifyDeployments: true])
    }
}

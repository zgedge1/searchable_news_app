pipeline {
    agent any

    environment {
        JAVA_HOME = tool 'JDK8'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn clean package"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    sh "${mvnHome}/bin/mvn test"
                }
            }
        }

        stage('Deploy') {
            steps {
                // No specific steps for deployment yet
            }
        }
    }

    post {
        success {
            echo 'Build successful - Triggering deployment'
            // You can add additional steps here for deployment
        }

        failure {
            echo 'Build failed - Notify team or take corrective actions'
            // You can add additional steps here for notifications or corrective actions
        }
    }
}

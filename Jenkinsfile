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
                sh 'mvn clean package'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Deploy') {
            steps {
                // Assuming you have a deployment step, you can add it here
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

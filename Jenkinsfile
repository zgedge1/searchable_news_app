pipeline {
    agent any



    stages {
        stage('Build') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def javaHome = tool 'JDK11'

                    env.PATH = "${javaHome}/bin:${mvnHome}/bin:${env.PATH}"

                    dir('home/zach/Documents/Java_Code/newsapp12') {
                        sh "mvn clean package"

                    }

                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def javaHome = tool 'JDK11'

                    env.PATH = "${javaHome}/bin:${mvnHome}/bin:${env.PATH}"

                    sh "mvn test"
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    def mvnHome = tool 'Maven'
                    def javaHome = tool 'JDK11'

                    env.PATH = "${javaHome}/bin:${mvnHome}/bin:${env.PATH}"

                    sh "java -jar target/newsapp12-hellonews.jar.jar"
                }
            }
        }
    }

    post {
        success {
            echo 'Build succeeded! Deploying...'
        }
        failure {
            echo 'Build failed! Not deploying...'
        }
    }
}

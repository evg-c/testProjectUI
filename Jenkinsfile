pipeline {
    agent any

    tools {
        maven 'mvnForJenkins'
    }
    environment {
        BROWSER = 'chrome'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build & Unit Tests') {
            steps {
                sh 'mvn verify -Punit'
            }
        }
        stage('Integration & API Tests') {
            steps {
                sh 'mvn verify -Pintegration'
            }
            post {
                always {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/*.log', allowEmptyArchive: true
        }
    }
}
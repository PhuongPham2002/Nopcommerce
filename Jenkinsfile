pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/PhuongPham2002/Nopcommerce', branch: 'master'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo '🟡 Post actions bắt đầu...'
            allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
        }
    }
}

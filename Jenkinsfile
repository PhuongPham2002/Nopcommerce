pipeline {
	agent any
	parameters {
	choice (name: 'SUITE', choices:['testng-smoke.xml','testng-regression.xml','testng-sanity.xml' ], description: 'Chọn suite cần chạy')
		booleanParam(name: 'HEADLESS', defaultValue: true, description: 'Chạy headless mod')
	}
	stages {
		 stage('Info') {
            steps {
                echo "🚀 Đang chạy trên nhánh: ${env.BRANCH_NAME}"
            }
        }
		stage ('Clean workspace'){
			steps {
				cleanWs()
			}
		}
		stage ('checkout') {
			steps {
				checkout scm
			}
		}
		stage('Run Tests') {
            steps {
                script {
                       if (isUnix()) {
                         if (env.BRANCH_NAME == 'dev') {
                            sh "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                         } else if (env.BRANCH_NAME == 'staging') {
                            sh "mvn clean test -DsuiteXmlFile=testng-regression.xml"
                         } else if (env.BRANCH_NAME == 'prod') {
                            sh "mvn clean test -DsuiteXmlFile=testng-full.xml"
                         } else {
                            sh "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                         }
                    } else {
                        // Windows
                         if (env.BRANCH_NAME == 'dev') {
                            bat "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                         } else if (env.BRANCH_NAME == 'staging') {
                            bat "mvn clean test -DsuiteXmlFile=testng-regression.xml"
                         } else if (env.BRANCH_NAME == 'prod') {
                            bat "mvn clean test -DsuiteXmlFile=testng-full.xml"
                         } else {
                         bat "mvn clean test -DsuiteXmlFile=testng-smoke.xml"
                        }
                    }
                }
            }
        }
    }
    post {
        always {
            allure([
                includeProperties: false,
                jdk: '',
                 commandline: 'allure_2.30.0',
                 reportBuildPolicy: 'ALWAYS',
                results: [[path: 'allure-results']]
            ])
        }
    }
}

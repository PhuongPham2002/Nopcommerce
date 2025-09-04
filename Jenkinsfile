pipeline {
	agent any
	parameters {
	choice(name:'SUITE', choices:['src/test/resources/testng-smoke.xml','src/test/resources/testng-regression.xml'],description:'choose test suite to run')
	choice(name:'BROWSER', choices:['chrome','firefox','edge'], description:'Choose browser to run test')
    choice(name: 'TEST_ENV', choices: ['dev','staging','production'], description: 'Choose testing environment to run test')
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
                            sh "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else if (env.BRANCH_NAME == 'staging') {
                            sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else if (env.BRANCH_NAME == 'master') {
                            sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else {
                            sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         }
                    } else {
                        // Windows
                         if (env.BRANCH_NAME == 'dev') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else if (env.BRANCH_NAME == 'staging') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else if (env.BRANCH_NAME == 'master') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
                         } else {
                           bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${params.SUITE}"
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

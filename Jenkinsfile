pipeline {
	agent any
	parameters {
	choice(name: 'RUN_MODE', choices: ['single','cross'], description: 'Chạy 1 browser hay cross-browser?'
	choice(name:'SUITE', choices:['testng-smoke.xml','testng-regression.xml'],description:'choose test suite to run')
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
		stage('Cross Browser Tests'){
		  when {
                expression { params.RUN_MODE == 'cross' }
            }
		    matrix{
		        axes{
		            axis{
		                name 'BROWSER'
		                values 'chrome','firefox','edge'
		            }
		        }
		    }
		    stage('Run Tests') {
                     script {
                        def suitePath = "src/test/resources/${params.SUITE}"
                            if (isUnix()) {
                            if (env.BRANCH_NAME == 'dev') {
                               sh "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else if (env.BRANCH_NAME == 'staging') {
                               sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else if (env.BRANCH_NAME == 'master') {
                               sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else {
                               sh "mvn clean test -DtestEnv=${params.TEST_ENV} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            }
                                } else {
                                    // Windows
                            if (env.BRANCH_NAME == 'dev') {
                               bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else if (env.BRANCH_NAME == 'staging') {
                               bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else if (env.BRANCH_NAME == 'master') {
                               bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                            } else {
                              bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                                    }
                                }
                            }
                        }
                    }
                }

		stage('Run Tests') {
            steps {
                script {
                def suitePath = "src/test/resources/${params.SUITE}"
                       if (isUnix()) {
                         if (env.BRANCH_NAME == 'dev') {
                            sh "mvn clean test -Dbrowser=${BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else if (env.BRANCH_NAME == 'staging') {
                            sh "mvn clean test -Dbrowser=${BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else if (env.BRANCH_NAME == 'master') {
                            sh "mvn clean test -Dbrowser=${BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else {
                            sh "mvn clean test -Dbrowser=${BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         }
                    } else {
                        // Windows
                         if (env.BRANCH_NAME == 'dev') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else if (env.BRANCH_NAME == 'staging') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else if (env.BRANCH_NAME == 'master') {
                            bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
                         } else {
                           bat "mvn clean test -Dbrowser=${params.BROWSER} -DtestEnv=${params.TEST_ENV} -DsuiteXmlFile=${suitePath}"
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

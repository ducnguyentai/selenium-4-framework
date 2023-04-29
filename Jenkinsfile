pipeline {
    agent any
    stages {
        stage('Clone and Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/ducnguyentai/selenium-4-framework.git'

                bat 'mvn clean test -Dcucumber.filter.tags="@regression"'
            }
        }
        stage('Generate report') {
            steps {
                bat "allure generate --clean"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'allure-report', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: 'Report', useWrapperFileDirectly: true])
                }
            }
        }
    }
}
pipeline {
  agent any
  stages {
    stage('- - -clean- - -') {
      steps {
       sh "mvn clean"
      }
    }
    stage('- - - test- - - ') {
      steps {
        sh "mvn test-compile gauge:execute -DspecsDir=specs -Dtags='PlanYourJourney'"
    }
        }

    stage('- - package - -') {
      steps {
        sh "mvn package"
      }
    }
  }
}
pipeline {
    agent any

    tools {
        maven 'M2_HOME'
    }



    stages {
        stage('Checkout Git repository') {
            steps {
                echo 'Pulling'
                git branch: 'khouloud', credentialsId: 'MyPersonalToken', url: 'https://github.com/aymeennefzi/CI-CD.git'            }
        }

        stage('Maven Clean Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Tests JUnit/Mockito ') {
                            steps {
                                // Exécution des tests unitaires avec Maven
                                sh 'mvn test'
                            }
                        }


        stage('Build package') {
            steps {
                sh 'mvn package'
            }
        }



        stage('Maven Install') {
            steps {
                sh 'mvn install'
            }
        }

         stage('Generate JaCoCo Report') {
                           steps {
                               // Exécuter les commandes pour générer le rapport Jacoco
                               sh 'mvn jacoco:report'
                           }
                           post {
                               always {
                                   // Archiver le rapport Jacoco pour pouvoir y accéder après l'exécution du pipeline
                                   jacoco(execPattern: 'target/jacoco.exec')
                               }
                           }
                       }

                    stage('Publish JaCoCo Report') {
                                steps {
                                    // Publier le rapport HTML JaCoCo
                                    publishHTML(
                                        target: [
                                            allowMissing: false,
                                            alwaysLinkToLastBuild: true,
                                            keepAll: true,
                                            reportDir: 'target/site/jacoco/',
                                            reportFiles: 'index.html',
                                            reportName: 'JaCoCo Code Coverage Report'
                                        ]
                                    )
                                }
                            }

stage('SonarQube Analysis') {
    steps {
        script {

                def scannerHome = tool name: 'sonarscanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'

                withEnv(["SONAR_SCANNER_HOME=${scannerHome}"]) {
                    // Execute SonarQube scanner
                    sh """
                        ${scannerHome}/bin/sonar-scanner \
                        -Dsonar.projectName=DevOps_Project \
                        -Dsonar.java.binaries=. \
                        -Dsonar.projectKey=tn.esprit:DevOps_Project \
                        -Dsonar.host.url=http://192.168.33.10:9000 \
                        -Dsonar.login=squ_c7e6ab1f5213359c2dce6cdadac3c49cde1bdffc
                    """
                }

        }
    }
}
            stage('Deploy to Nexus') {
                        steps {
                            script {

                                    sh 'mvn deploy -DskipTests --settings /usr/share/maven/conf/settings.xml -e'

                            }
                        }
            }

  stage('Build Docker image') {
                        steps {
                            script {
                                def imageName = 'khouloudk23/devops_project:latest'
                                sh "docker build -t ${imageName} ."
                            }
                        }
                    }

                  stage('Deploy Image') {
                      steps {
                          withCredentials([string(credentialsId: 'DevopsPwd', variable: 'DOCKER_PASSWORD')]) {
                              sh '''
                              echo "${DOCKER_PASSWORD}" | docker login -u khouloudk23 --password-stdin
                              docker push khouloudk23/devops_project:latest
                              '''
                          }
                      }
                  }

                   stage('Docker Compose Up') {
                              steps {
                              script{
                                  sh "docker compose down"

                                  sh "docker compose up -d"

                                    }
                              }
                          }



}
}

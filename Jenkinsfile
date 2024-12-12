pipeline {
    agent any

    tools {
        maven "Maven 3.9.9"
    }
    environment{
        DOCKER_HUB_REPO = 'yorften/musicapi'
    }


    stages {
        stage('clone repo'){
            steps{
                git branch : 'main', url: 'https://github.com/JavaAura/Abderrahman_Badi_Sprint_4_B2_MusicAPI'
            }
        }
        stage('Build Artifact'){
            steps{
                sh '''
                    cd ./musicapi
                    mvn -B -DskipTests clean package 
                '''
                archiveArtifacts artifacts: 'musicapi/target/*.jar', fingerprint: true
                stash name: 'jar-artifact', includes: 'musicapi/target/*.jar'
            }
        }
        
        stage('Docker Build') {
            steps {
                unstash 'jar-artifact' 
                sh "docker build -t ${DOCKER_HUB_REPO}:latest ."
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'docker-login', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin'
                    }
                    
                    sh "docker push ${DOCKER_HUB_REPO}:latest"
                }
            }
        }
    }
}

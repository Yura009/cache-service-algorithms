pipeline {
    agent any

    parameters {
        choice(name: 'BUILD_TOOL', choices: ['maven', 'gradle'], description: 'Choose build tool')
    }

    options {
        disableConcurrentBuilds()
        buildDiscarded(logRotator(numToKeepStr: '10'))
    }

    stages {

        stage('Release Build') {
            steps {
                script {
                    if (params.BUILD_TOOL == 'maven') {
                        sh "mvn clean install"
                    } else {
                        sh "./gradlew clean build"
                    }
                }
            }
            post {
                success {
                    echo "Release build successful, applying QA star"
                }
            }
        }

        stage('Continuous Build (nightly)') {
            when {
                expression {
                    new Date().format("HHmm") == "0200"
                }
            }
            steps {
                echo "Running nightly continuous build"
                script {
                    if (params.BUILD_TOOL == 'maven') {
                        sh "mvn clean install"
                    } else {
                        sh "./gradlew clean build"
                    }
                }
            }
        }

        stage('Deploy QA') {
            steps {
                echo "Deploying QA artifact using Container Deploy plugin"
                sh "echo Deploying QA artifact..."
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    sh "curl -f http://localhost:8080/cache-service-algorithms || exit 1"
                }
            }
        }

        stage('Deploy to Cloud') {
            steps {
                input message: "Manual promotion to Prod?", ok: "Deploy"
                echo "Deploying promoted artifact to cloud machine"
                sh "echo Deploying to Tomcat on cloud..."
            }
        }
    }

    post {
        success {
            script {
                sh """
                echo 'Incrementing minor version and tagging...'
                git config user.name "jenkins"
                git config user.email "jenkins@example.com"
                VERSION_FILE=version.txt
                if [ ! -f \$VERSION_FILE ]; then echo "0.0" > \$VERSION_FILE; fi
                VERSION=\$(cat \$VERSION_FILE)
                MAJOR=\$(echo \$VERSION | cut -d. -f1)
                MINOR=\$(echo \$VERSION | cut -d. -f2)
                NEW_MINOR=\$((MINOR+1))
                NEW_VERSION="\$MAJOR.\$NEW_MINOR"
                echo \$NEW_VERSION > \$VERSION_FILE
                git add \$VERSION_FILE
                git commit -m "Increment version to \$NEW_VERSION [ci skip]" || true
                git tag v\$NEW_VERSION
                git push origin HEAD --tags
                """
            }
        }
        failure {
            mail to: 'yurii@gmail.com, roman@gmail.com',
                 subject: "Jenkins build failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                 body: "See details: ${env.BUILD_URL}"
        }
    }
}

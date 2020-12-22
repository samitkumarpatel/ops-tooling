# jenkins jnlp agent automation
The same way you can setup several other jnlp agent along with jenkins docker image and setup your jenkins environment. you can find more jnlp details from [dockerhub](https://hub.docker.com/u/jenkins)

### How To Start
```sh
./start.sh
(or) make start
```
> There are several user , which are created while starting Jenkins by using jcac plugins. for `root` user find the password from jenkins logs by typing `docker-compose logs -f`. For `admin` user the password is `admin123`. for other details look inot jcac.yml file

### To Test All Your agents are working or not, Follow the steps below
- Create a pipeline Job
- Copy the below content and past it on the pipeline sandbox
```
pipeline {
    agent none
    stages {
        stage('docker') {
            agent {
                label 'docker'
            }
            steps {
                sh 'docker images'
            }
        }
        stage('parallel') {
            parallel {
                stage('maven') {
                    agent {
                        label "maven"
                    }
                    steps {
                        sh "mvn -v"
                    }
                }
                stage('terraform') {
                    agent {
                        label "terraform"
                    }
                    steps {
                        sh "terraform --version"
                    }
                }
                stage('docker') {
                    agent {
                        label "docker"
                    }
                    steps {
                        sh "docker images"
                    }
                }
            }
        }
    }
}
```
- run the pipeline and check the output

### How To Stop
```sh
make stop
(or) docker-compose stop
```

### How To Clean
```sh
make clean
(or) docker-compose down -v
```
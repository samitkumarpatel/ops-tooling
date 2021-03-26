docker-compose up -d jcac dind jenkins
sleep 30
export MAVEN_TOKEN=$(curl -L -s -u admin:admin123 -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://localhost:8080/computer/maven/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/")
echo "Got MAVEN_TOKEN: $MAVEN_TOKEN Successfully"
export TERRAFORM_TOKEN=$(curl -L -s -u admin:admin123 -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://localhost:8080/computer/terraform/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/")
echo "Got TERRAFORM_TOKEN: $TERRAFORM_TOKEN Successfully"
export DOCKER_TOKEN=$(curl -L -s -u admin:admin123 -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://localhost:8080/computer/docker/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/")
echo "Got DOCKER_TOKEN: $DOCKER_TOKEN Successfully"
export PYTHON_TOKEN=$(curl -L -s -u admin:admin123 -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://localhost:8080/computer/python/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/")
echo "Got PYTHON_TOKEN: $PYTHON_TOKEN Successfully"
docker-compose up -d maven docker terraform python

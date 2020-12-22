## Jenkins Automation

**Build Your Own Jenkins With Plugins**
```
FROM jenkins/jenkins:lts
RUN jenkins-plugin-cli --plugins kubernetes workflow-aggregator git configuration-as-code
```
**groovy**

This folder contain some helpful grrovy script

**jcac**

Jenkins Code As Config related config file, which will help us to create automate way of `node`, `creadential`, `view`, `permission`, `security` while provision Jenkins with docker-compose.

**docker-compose**

This automation we are acheiving through `docker-compose`. This will take care.

1. Provision a new Jenkins based on the Version and volume specify on `dockdr-compose.yml`.
2. This also will take care
    - Plugins Installation - we have a groovy script for that , which can be found in `init.groovy.d`
    - An admin user called `root` will create , after plugins installation. The password for root user can be found in the Jenkins Home directory. For more details reafer Jenkins log     
3. We use `jenkins-configuration-as-code` to take care allmost all admin related activity as code. More details for this can be found in `jenkins-casc.yml`. To extend this file , please refer `jenkins-configuration-as-code` official documantation. 

To use:

```
docker-compose -f docker-compose.yml up -d
docker-compose up -d
docker-compose logs -f

docker-compose down -v
```
For more useful docker-compose command , Please follow `docker-compose` [official docs](https://docs.docker.com/compose/)


**Jenkins JobBuilder**

docs is on progress.

**Some Jenkins Tips**

- To Get JenkinsNode seceret for automation
```
curl -L -s -u USERNAME:PASSWORD -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://HOST:PORT/computer/SLAVENAME/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/"
```
or [follow this ](https://support.cloudbees.com/hc/en-us/articles/222520647-How-to-find-JNLP-Node-s-secret-key-remotely-)

### Jenkins Automation

If you have a prebuild Jenkins docker images with Jenkins code as config and other related plugins, Its easy to automate with out any human intervation
Like :

Let say you habve a below Jcac file

```
credentials:
  system:
    domainCredentials:
      - credentials:
        - usernamePassword:
            id: "bb_token"
            password: ${BB_ACCESS_TOKEN}
            scope: GLOBAL
            username: "samitkumarpatel@gmail.com"
        - usernamePassword:
            id: "nexus_token"
            password: ${NEXUS_ACCESS_TOKEN}
            scope: GLOBAL
            username: "Samitkumar.Patel"
        - string:
            id: "sonarqube"
            scope: GLOBAL
            secret: ${SONARQUBE_TOKEN}
jenkins:
  systemMessage: "Hello, world!"
  agentProtocols:
    - "JNLP4-connect"
    - "Ping"
  crumbIssuer:
    standard:
      excludeClientIPFromCrumb: true
  disableRememberMe: false
  markupFormatter: "plainText"
  mode: NORMAL
  myViewsTabBar: "standard"
  numExecutors: 1
  nodes:
    - permanent:
        labelString: "jnlpSlave"
        launcher:
          jnlp:
            workDirSettings:
              disabled: false
              failIfWorkDirIsMissing: false
              internalDir: "remoting"
        name: "jnlpSlave"
        nodeDescription: "jnlpSlave"
        numExecutors: 3
        remoteFS: "/tmp"
        retentionStrategy: "always"
    - permanent:
        labelString: "jnlpDockerSlave"
        launcher:
          jnlp:
            workDirSettings:
              disabled: false
              failIfWorkDirIsMissing: false
              internalDir: "remoting"
        name: "jnlpDockerSlave"
        nodeDescription: "jnlpDockerSlave"
        numExecutors: 3
        remoteFS: "/tmp"
        retentionStrategy: "always"
    - permanent:
        labelString: "sshSlave"
        launcher:
          ssh:
            credentialsId: "ssh"
            host: "worker"
            port: 22
            sshHostKeyVerificationStrategy: "nonVerifyingKeyVerificationStrategy"
        name: "sshSlave"
        nodeDescription: "sshSlave"
        numExecutors: 3
        remoteFS: "/tmp"
        retentionStrategy: "always"
  securityRealm:
    local:
      allowsSignup: false
      enableCaptcha: false
      users:
        - id: "samit"
          password: "samit123"
        - id: "other"
          password: "other123"
  authorizationStrategy:
    roleBased:
      roles:
        global:
          - name: "admin"
            pattern: ".*"
            permissions:
              - "Job/Move"
              - "Job/Build"
              - "Lockable Resources/View"
              - "Credentials/Delete"
              - "Credentials/ManageDomains"
              - "Lockable Resources/Unlock"
              - "View/Create"
              - "Agent/Configure"
              - "Job/Read"
              - "Credentials/Update"
              - "Agent/Create"
              - "Job/Delete"
              - "Agent/Build"
              - "View/Configure"
              - "Lockable Resources/Reserve"
              - "Agent/Provision"
              - "SCM/Tag"
              - "Job/Create"
              - "Job/Discover"
              - "Credentials/View"
              - "Agent/Connect"
              - "Agent/Delete"
              - "Run/Replay"
              - "Agent/Disconnect"
              - "Run/Delete"
              - "Job/Cancel"
              - "Overall/Read"
              - "Run/Update"
              - "Credentials/Create"
              - "Overall/Administer"
              - "View/Delete"
              - "Job/Configure"
              - "Job/Workspace"
              - "View/Read"
            assignments:
              - "root"
              - "samit"
          - name: "user"
            pattern: ".*"
            permissions:
              - "Lockable Resources/View"
              - "Job/Build"
              - "SCM/Tag"
              - "Lockable Resources/Unlock"
              - "Job/Discover"
              - "Job/Read"
              - "Run/Replay"
              - "Run/Delete"
              - "Job/Cancel"
              - "Overall/Read"
              - "Run/Update"
              - "Job/Workspace"
              - "View/Read"
              - "Lockable Resources/Reserve"
            assignments:
              - "other"
unclassified:
  globalLibraries:
    libraries:
      - name: "pipeline-javaee-libs"
        defaultVersion: "feature/build-libs01"
        implicit: false
        retriever:
          modernSCM:
            scm:
              git:
                remote: "/var/git/pipeline-javaee-libs/.git"
                credentialsId: "bb_token"
                traits:
                  - "gitHubBranchDiscovery"

```

and the docker command after that will be:

````
docker run --name nginx --rm -p 8081:80 -v $(pwd)/jenkins.yml:/usr/share/nginx/html/jenkins.yml:ro --network jenkins nginx

docker run --name jenkins --network jenkins --rm -p 8080:8080 registry/jenkins-blueocean:1.22.0-17-03-2020 -e CASC_JENKINS_CONFIG=http://nginx:8080/jenkins.yml

docker run --rm --network jenkins jenkins/jnlp-slave -url http://jenkins:8080 $(curl -L -s -u samit:samit123 -H "Jenkins-Crumb:dac7ce5614f8cb32a6ce75153aaf2398" -X GET http://localhost:8080/computer/jnlpSlave/slave-agent.jnlp | sed "s/.*<application-desc main-class=\"hudson.remoting.jnlp.Main\"><argument>\([a-z0-9]*\).*/\1/") jnlpSlave

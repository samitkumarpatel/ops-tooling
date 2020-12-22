import jenkins.model.*

def jobName = "job01"
def binding = [
  repoUrl: 'https://github.com/hello-world-maven.git', 
  credentialId: 'BitBucket',
  branchToBuild: master
]
def text = '''\
<flow-definition plugin="workflow-job@2.26">
  <actions/>
  <description> Jochen </description>
  <keepDependencies>false</keepDependencies>
  <properties>
    <org.datadog.jenkins.plugins.datadog.DatadogJobProperty plugin="datadog@0.7.1">
      <tagProperties/>
      <tagFile/>
      <emitOnCheckout>false</emitOnCheckout>
    </org.datadog.jenkins.plugins.datadog.DatadogJobProperty>
    <com.sonyericsson.rebuild.RebuildSettings plugin="rebuild@1.29">
      <autoRebuild>false</autoRebuild>
      <rebuildDisabled>false</rebuildDisabled>
    </com.sonyericsson.rebuild.RebuildSettings>
  </properties>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition" plugin="workflow-cps@2.58">
    <scm class="hudson.plugins.git.GitSCM" plugin="git@3.9.1">
      <configVersion>2</configVersion>
      <userRemoteConfigs>
        <hudson.plugins.git.UserRemoteConfig>
        <url>$repoUrl</url>
        <credentialsId>$credentialId</credentialsId>
        </hudson.plugins.git.UserRemoteConfig>
      </userRemoteConfigs>
      <branches>
        <hudson.plugins.git.BranchSpec>
        <name>*/$branchToBuild</name>
        </hudson.plugins.git.BranchSpec>
      </branches>
      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
      <submoduleCfg class="list"/>
      <extensions/>
    </scm>
    <scriptPath>Jenkinsfile</scriptPath>
    <lightweight>true</lightweight>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>
'''
def engine = new groovy.text.XmlTemplateEngine()
def template = engine.createTemplate(text).make(binding)
println template.toString()

def xmlStream = new ByteArrayInputStream(template.toString().getBytes())
Jenkins.instance.createProjectFromXML(jobName,xmlStream)

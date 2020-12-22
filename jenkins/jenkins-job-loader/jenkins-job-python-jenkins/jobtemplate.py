NON_SCM_PIPELINE = '''<?xml version='1.0' encoding='UTF-8'?>
<flow-definition plugin="workflow-job@2.35">
  <description>$desc</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps@2.74">
    <script>
      {{ script }}
    </script>
    <sandbox>{{ sandbox }}</sandbox>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>'''

SCM_PIPELINE = '''<?xml version='1.0' encoding='UTF-8'?>
<flow-definition plugin="workflow-job@2.26">
  <actions/>
  <description>{{ description }}</description>
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
          <url>{{ clone_url }}</url>
          <credentialsId>{{ credentialId }}</credentialsId>
        </hudson.plugins.git.UserRemoteConfig>
      </userRemoteConfigs>
      <branches>
        <hudson.plugins.git.BranchSpec>
        <name>*/{{ branch }}</name>
        </hudson.plugins.git.BranchSpec>
      </branches>
      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
      <submoduleCfg class="list"/>
      <extensions/>
    </scm>
    <scriptPath>{{ filename | 'Jenkinsfile' }}</scriptPath>
    <lightweight>true</lightweight>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>'''
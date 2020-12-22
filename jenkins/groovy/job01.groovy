import jenkins.model.*

def binding = [desc: 'Jochen']
def engine = new groovy.text.XmlTemplateEngine()
def text = '''\
<flow-definition plugin="workflow-job@2.35">
  <description>$desc</description>
  <keepDependencies>false</keepDependencies>
  <properties/>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps@2.74">
    <script>
      node {
        stage("echo") {
          sh """
            echo "Hello World!"
          """
        }
      }
    </script>
    <sandbox>true</sandbox>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>
'''
def template = engine.createTemplate(text).make(binding)
println template.toString()

def jobName = "job01"
def xmlStream = new ByteArrayInputStream(template.toString().getBytes())

Jenkins.instance.createProjectFromXML(jobName,xmlStream)
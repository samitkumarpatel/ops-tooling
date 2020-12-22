import jenkins.model.*
import java.util.logging.Level
import java.util.logging.Logger

def logger = Logger.getLogger("install-plugins")
def installed = false
def initialized = false
def plugins_basic_with_version = [
    "cloudbees-folder:6.9",
    "antisamy-markup-formatter:1.6",
    "build-timeout:1.19",
    "credentials-binding:1.20",
    "timestamper:1.10",
    "ws-cleanup:0.37",
    "ant:1.10",
    "gradle:1.34",
    "workflow-aggregator:2.6",
    "github-branch-source:2.5.8",
    "pipeline-github-lib:1.0",
    "pipeline-stage-view:2.12",
    "git:3.12.1",
    "subversion:2.12.2",
    "ssh-slaves:1.31.0",
    "matrix-auth:2.5",
    "pam-auth:1.6",
    "ldap:1.20",
    "email-ext:2.68",
    "mailer:1.29",
    "role-strategy:2.15",
    "configuration-as-code:1.32",
    "description-setter:1.10"
]

def plugins_basic = [
    "cloudbees-folder",
    "antisamy-markup-formatter",
    "build-timeout",
    "credentials-binding",
    "timestamper",
    "ws-cleanup",
    "ant",
    "gradle",
    "workflow-aggregator",
    "github-branch-source",
    "pipeline-github-lib",
    "pipeline-stage-view",
    "git",
    "subversion",
    "ssh-slaves",
    "matrix-auth",
    "pam-auth",
    "ldap",
    "email-ext",
    "mailer",
    "role-strategy",
    "configuration-as-code",
    "description-setter"
]

def instance = Jenkins.getInstance()
def pluginsManager = instance.getPluginManager()
def updateCenter = instance.getUpdateCenter()
plugins_basic.each {
    logger.log(Level.INFO,"{0} plugins installation triggered",it)
    if (!pluginsManager.getPlugin(it)) {
        logger.log(Level.INFO,"Looking UpdateCenter for : {0}",it)
        if (!initialized) {
            updateCenter.updateAllSites()
            initialized = true
        }
        def plugin = updateCenter.getPlugin(it)
        if (plugin) {
            logger.info("Installing - "+ it)
            def installFuture = plugin.deploy()
            while(!installFuture.isDone()) {
                logger.log(Level.INFO,"Waiting for {0} plugin top be install", it)
                sleep(3000)
            }
            installed = true
        }
    }
}
if (installed) {
    logger.log(Level.INFO,"***********************PLUGINS INSTALLATION COMPLETED**************************")
    instance.save()
    instance.restart()
}
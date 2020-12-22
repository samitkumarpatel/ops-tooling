# https://python-jenkins.readthedocs.io/en/latest/ - documents to follow
import jenkins
import jobtemplate
from jinja2 import Template
import yaml

server = jenkins.Jenkins(url='http://192.168.99.103:8080',username='samit',password='samit123')

def seperate():
    print "============================================="

# Job Count
def job_count():
    print server.jobs_count()

# Jobs Details 
def get_all_jobs():
    jobs = server.get_jobs()
    for job in jobs:
        print job['name']

# Create Job
# server.create_job('job01', jobtemplate.NON_SCM_PIPELINE)
def create_job(name=None,teamplate=None,data=[]):
    t = Template(teamplate).render(data)
    if(server.job_exists(name)):
        # update
        print server.reconfig_job(name,t)        
    else:
        # new
        print server.create_job(name, t)

# readYml
def readYaml(file):
    with open(file, 'r') as stream:
        try:
            return yaml.safe_load(stream)
        except yaml.YAMLError as exc:
            print(exc)
            return []

# Plugins
def get_all_plugins():
    plugins = server.get_plugins_info()
    for plugin in plugins:
        print "{0}:{1}".format(plugin['shortName'],plugin['version'])

# nodes
def get_all_nodes():
    nodes = server.get_nodes()
    node_name=[]
    for node in nodes:
        node_name.append(node['name'])
        print "Name: {0}, status: {1}".format(node['name'], 'Offline' if node['offline'] else 'Online')
    return node_name

def create_node(name=None):
    if(name in get_all_nodes()):
        pass
    else:
        server.create_node(name,numExecutors=3,launcher=jenkins.LAUNCHER_JNLP)

if __name__ == "__main__":
    #job_count()
    #seperate()

    for job in readYaml("job-input-01.yml"):
        create_job(job['name'], jobtemplate.NON_SCM_PIPELINE,job)
    seperate()

    #get_all_jobs()
    #seperate()

    #get_all_plugins()
    #seperate()

    #get_all_nodes()
    #seperate()

    r = create_node("slave02")
    print r
    seperate()

    try:
        rr = server.get_node_config("slave01")
        print rr
    except Exception as e:
        print "migth be slave not found: {0}".format(str(e))
    seperate()

    get_all_plugins()
    seperate()

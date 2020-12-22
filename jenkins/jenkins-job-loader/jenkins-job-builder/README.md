### Jenkins Job Builder - Automation

[click](https://pypi.org/project/jenkins-job-builder/) here to navigate to the official docs.

* The needed configuration for this lib is :
[Official docs](https://docs.openstack.org/infra/jenkins-job-builder/execution.html)

```
[job_builder]
allow_duplicates = False
keep_descriptions = False
ignore_cache = True
recursive = False
update = all
[jenkins]
password = samit123_
query_plugins_info = False
url = http://localhost:8080/
user = samit
[jenkins_remote]
query_plugins_info = False
url = http://localhost:8080
```
* supported command can be found on 
[command](https://docs.openstack.org/infra/jenkins-job-builder/execution.html#running)

* Create Jenkins Job
[Official docs](https://docs.openstack.org/infra/jenkins-job-builder/definition.html)

Check `job.yml` file for sample.
To make this possible , the command is 

Test the config and inputs are correct or not, run below
```
jenkins-jobs --conf jenkins-jobs.ini test job.yml
```
Create the Job
```
jenkins-jobs --conf jenkins-jobs.ini update job.yml
```
List all the Job which are part of Jenkins Job builder
```
jenkins-jobs --conf config.ini --server jenkins_remote --user $JENKINS_USER --password $JENKINS_PASSWORD list -p jobs/
```

List all the Job available in Jenkins
```
jenkins-jobs --conf config.ini --server jenkins_remote --user $JENKINS_USER --password $JENKINS_PASSWORD list -p jobs/
```

Delete all Job and Views
```
jenkins-jobs --conf config.ini --server jenkins_remote --user $JENKINS_USER --password $JENKINS_PASSWORD delete-all
```

Delete a Job/view

```
jenkins-jobs --conf jenkins-jobs.ini delete name <jobName>
```

Delete all the Job which are part of the job builder configuration
```
jenkins-jobs --conf config.ini --server jenkins_remote --user $JENKINS_USER --password $JENKINS_PASSWORD delete name $(jenkins-jobs --conf config.ini --server jenkins_remote --user $JENKINS_USER --password $JENKINS_PASSWORD list -p jobs/)
```

* module
[Official Module](https://docs.openstack.org/infra/jenkins-job-builder/definition.html#modules)

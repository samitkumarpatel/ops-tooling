### Jenkins Local

Jenkins local is for to use jenkins for local shared library development, any other jenkins automation like job-builder, free style job, pipeline job.

**How yo use this?**

- Start (for first time)
```
docker-compose up --build -d
docker-compose start
```

- To see the logs
```
docker-compose logs -f
```

- Stop 
```
docker-compose stop
```

- Remove (clean)
```
docker-compose down --rmi all
```

**Tips**
By default, by help of init.groovy and jcac plugins , when you start the compose first time it will create total 3 user and these are :
- admin / admin123 with ADMIN access.
- user / user123 with minimal access.
- root /<PASSWORD CAN BE RETRIVE FROM LOGS> with ADMIN access.

**About the Files in this folder**
- docker-compose.yml - is for docker-compose tool for orchastration process
- Dockerfile - is for Docker Image
- helpful.groovy - Just a groovy snippit to do some admin work. this script can be run on scriptConsole after jenkins start
- jcac.yml - Jenkins code as config configuration
- Jenkinsfile - Jenkins file for Jenkins , which will moved to shared library soon
- plugins.txt - plugins to be installed while create the jenkins image with `docker-compose up --build` command.
- README.md - README file
- user.groovy - will help to create a root user for Jenkins for administrator project.
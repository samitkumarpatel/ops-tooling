### Jenkins

To build and push the defined docker image to github package follow the below steps

For more details , [How to use github package](https://github.com/features/packages)

```
docker login docker.pkg.github.com --username samitkumarpatel@gmail.com

docker build -t docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins:latest .
docker tag docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins:latest docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins:$(date +'%d-%m-%Y-%H%M')
docker push docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins
```

From the above example `samitkumarpatel`is the github account/profile name and `devops-tooling` is a repository under github.com/samitkumarpatel account. 


for hub.docker.com the build flow will be

```
docker login
username:
password:

docker build -t samitkumarpatel/jenkins:latest .
docker tag samitkumarpatel/jenkins:latest samitkumarpatel/jenkins:$(date +'%d-%m-%Y-%H%M')
docker push samitkumarpatel/jenkins
```

### Jenkins-jcac

To build and push the defined docker image to github package follow the below steps

For more details , [How to use github package](https://github.com/features/packages)

```
docker login docker.pkg.github.com --username samitkumarpatel@gmail.com 

docker build -t docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins-jcac:latest .
docker tag docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins-jcac:latest docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins-jcac:$(date +'%d-%m-%Y-%H%M')
docker push docker.pkg.github.com/samitkumarpatel/devops-tooling/jenkins-jcac
```

From the above example `samitkumarpatel`is the github account/profile name and `devops-tooling` is a repository under github.com/samitkumarpatel account.

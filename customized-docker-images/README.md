
This folder contain docker file for prebuild Jenkins and other supported image

This Image we are storing in github docker registry, Below are some useful information.

[GitHub Package Registry](https://github.com/features/package-registry)

Helpful command around docker build

>Make sure you have a valid token with `['write:packages']` access

>Make sure , you are tagging the image with `:owner/:repo_name/:image_name` name

Jenkins

```
tag=$(date +%Y-%m-%d-%H-%M-%S)
docker build -t docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins:2.190.1-$tag .
docker tag docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins:2.190.1-$tag docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins:latest
docker login docker.pkg.github.com --username samitkumarpatel

docker push docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins:2.190.1-$tag
docker push docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins:latest
```

Blueocean

```
tag=$(date +%Y-%m-%d-%H-%M-%S)
docker build -f Dockerfile.blueocean -t docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins-blueocean:1.19.0-$tag .
docker tag docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins-blueocean:1.19.0-$tag docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins-blueocean:latest
docker login docker.pkg.github.com --username samitkumarpatel

docker push docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins-blueocean:1.19.0-$tag
docker push docker.pkg.github.com/samitkumarpatel/devops-tools/jenkins-blueocean:latest
```

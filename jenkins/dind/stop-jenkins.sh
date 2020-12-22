#!/bin/bash

docker stop jenkins-blueocean
docker stop jenkins-docker

docker network rm jenkins

docker volume rm jenkins-docker-certs
docker volume rm jenkins-data
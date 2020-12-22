FROM ubuntu:18.04
ARG USER_ID=1000
ARG GROUP_ID=1000

RUN apt-get update && apt-get install -y openjdk-8-jdk curl wget

RUN addgroup --gid $GROUP_ID jenkins
RUN adduser --disabled-password --gecos '' --uid $USER_ID --gid $GROUP_ID jenkins

RUN mkdir -p /var/lib/jenkins
RUN chown -R $USER_ID:$GROUP_ID /var/lib/jenkins/

USER jenkins
WORKDIR /var/lib/jenkins

ENV JENKINS_URL=http://localhost:8080
ENV JENKINS_USERNAME=admin
ENV JENKINS_PASSWORD=fake
ENV SLAVE_NAME=unknown

RUN wget https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/3.9/swarm-client-3.9.jar
COPY --chown=jenkins:jenkins ./swarm-slave.sh .

ENTRYPOINT ["./swarm-slave.sh"]
CMD ["echo","Retry again..."]

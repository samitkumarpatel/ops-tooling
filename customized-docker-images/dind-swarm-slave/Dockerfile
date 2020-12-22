FROM alpine:latest
RUN apk update \
        && apk upgrade \
        && apk add --no-cache curl wget docker openjdk8

ENV JENKINS_URL="http://localhost:8080"
ENV JENKINS_USERNAME="master"
ENV JENKINS_PASSWORD="fake"
ENV SLAVE_NAME="unkonwnslave"

RUN mkdir /var/lib/jenkins
WORKDIR /var/lib/jenkins
RUN wget https://repo.jenkins-ci.org/releases/org/jenkins-ci/plugins/swarm-client/3.9/swarm-client-3.9.jar
COPY ./start.sh .

RUN chmod 777 /var/lib/jenkins/start.sh
RUN ls -al && cat /var/lib/jenkins/start.sh

ENTRYPOINT ["./start.sh"]
CMD ["echo","Process Completed"]

#!/usr/bin/env sh
java -jar /var/lib/jenkins/swarm-client-3.9.jar -master $JENKINS_URL -username $JENKINS_USERNAME -password $JENKINS_PASSWORD -name $SLAVE_NAME -retry 5 -retryInterval 5 -labels $SLAVE_NAME
exec "$@"
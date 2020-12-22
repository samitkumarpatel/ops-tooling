#FROM jenkins/jenkins:lts

FROM jenkins/jenkins:lts
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
#RUN jenkins-plugin-cli --plugins kubernetes workflow-aggregator git configuration-as-code
#RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

COPY user.groovy /usr/share/jenkins/ref/init.groovy.d/user.groovy
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt

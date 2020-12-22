FROM jenkins/jenkins:2.190.1
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
COPY user.groovy /usr/share/jenkins/ref/init.groovy.d/user.groovy
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
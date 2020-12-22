FROM jenkins/jenkins:lts
COPY z.user.groovy /usr/share/jenkins/ref/init.groovy.d/z.user.groovy
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt
RUN /usr/local/bin/install-plugins.sh < /usr/share/jenkins/ref/plugins.txt
ENV JAVA_OPTS="-Djenkins.install.runSetupWizard=false"

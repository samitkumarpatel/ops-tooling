FROM alpine:latest
ENV ANSIBLE_VERSION=2.8.5
RUN addgroup -g 1000 jenkins \
	&& adduser -u 1000 -G jenkins -D jenkins 
RUN set -xe \
    && echo "****** Install system dependencies ******" \
    && apk add --no-cache --progress python3 openssl \
		ca-certificates git openssh sshpass \
	&& apk --update add --virtual build-dependencies \
		python3-dev libffi-dev openssl-dev build-base \
	\
	&& echo "****** Install ansible and python dependencies ******" \
    && pip3 install --upgrade pip \
	&& pip3 install ansible==${ANSIBLE_VERSION} boto3 \
    \
    && echo "****** Remove unused system librabies ******" \
	&& apk del build-dependencies \
	&& rm -rf /var/cache/apk/* 

RUN set -xe \
    && mkdir -p /etc/ansible \
    && echo -e "[local]\nlocalhost ansible_connection=local" > \
        /etc/ansible/hosts
USER jenkins
CMD ["ansible", "--version"]

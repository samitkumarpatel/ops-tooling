FROM python:3.7-alpine
RUN apk update \
	&& apk upgrade \
	&& apk add gcc git libffi-dev musl-dev openssl-dev

RUN addgroup -g 1000 jenkins \
	&& adduser -u 1000 -G jenkins -D jenkins
USER jenkins
CMD ["python3","--version"]

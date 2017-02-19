FROM centos
MAINTAINER mooseliu@hotmail.co.uk

# step 1. install dependencies
RUN 	yum install -y m2crypto python-setuptools && \
		easy_install pip && \
		pip install shadowsocks

RUN 	yum install -y net-tools

# configuring step is in entrypoint
COPY	entrypoint.sh /
RUN 	chmod 755 /entrypoint.sh

# publish ports
EXPOSE	8388
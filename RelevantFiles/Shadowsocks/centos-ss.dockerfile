FROM centos:7.3
MAINTAINER mooseliu@hotmail.co.uk

WORKDIR /root
# install dependencies
RUN 	yum update -y && \
		yum install -y python-setuptools && \
		yum install -y git && \
		easy_install pip && \
		pip install git+https://github.com/shadowsocks/shadowsocks.git@master

# install tools and epel-release, vnstat to monitor network interface data flow
# vnstat version 1.15
RUN 	yum install -y net-tools epel-release && \
		yum repolist && \
		yum install -y vnstat

# collect entrypoint
COPY	ss.entrypoint.sh ./
RUN 	chmod 755 ./ss.entrypoint.sh

ENTRYPOINT ["./ss.entrypoint.sh"]
# publish ports
EXPOSE	8388
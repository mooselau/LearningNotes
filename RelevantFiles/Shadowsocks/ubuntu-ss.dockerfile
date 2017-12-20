FROM ubuntu:16.04
MAINTAINER mooseliu@hotmail.co.uk

WORKDIR /root
# install dependencies
RUN 	apt-get -y update && \
		apt-get -y install python-pip && \
		apt-get -y install git && \
		pip install git+https://github.com/shadowsocks/shadowsocks.git@master

RUN 	apt-get -y install net-tools

# collect entrypoint
COPY	ss.entrypoint.sh ./
RUN 	chmod 755 ./ss.entrypoint.sh

ENTRYPOINT ["./ss.entrypoint.sh"]
# publish ports
EXPOSE	8388
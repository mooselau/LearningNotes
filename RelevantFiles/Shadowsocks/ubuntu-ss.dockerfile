FROM ubuntu:16.04
MAINTAINER mooseliu@hotmail.co.uk

WORKDIR /root
# install dependencies
RUN 	apt-get -y update && \
		apt-get -y install python-pip && \
		apt-get -y install git && \
		pip install git+https://github.com/shadowsocks/shadowsocks.git@master

# install tools and vnstat to monitor network data flow
# vnstat version 1.14
RUN 	apt-get -y install net-tools vnstat

# collect entrypoint
COPY	ss.entrypoint.sh ./
RUN 	chmod 755 ./ss.entrypoint.sh

ENTRYPOINT ["./ss.entrypoint.sh"]
# publish ports
EXPOSE	8388
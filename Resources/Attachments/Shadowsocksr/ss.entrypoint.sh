#!/bin/bash
# Maintained by Moose Liu. (07.01.2018)

# Setting up variables if it is not set 
: ${password:=yourpassword}
: ${server_port:=8388}
: ${local_port:=1080}
: ${network_iface:=eth0}

# default is root user

if [ ! -e "/etc/shadowsocks.json" ] ; then
	echo "First time starts, configuration is ongoing.."

	echo "Creating database for vnstatd.."
	vnstat --create -i $network_iface

    echo "Starting monitoring traffic.."
    vnstat -u -i $network_iface

	echo "Generating config file for SS.."
	# Create config file
	touch /etc/shadowsocks.json
		echo "{" >> /etc/shadowsocks.json && \
		echo "	\"server\":\"0.0.0.0\"," >> /etc/shadowsocks.json && \
		echo "	\"server_port\":$server_port," >> /etc/shadowsocks.json && \
		echo "	\"local_address\":\"127.0.0.1\"," >> /etc/shadowsocks.json && \
		echo "	\"local_port\":$local_port," >> /etc/shadowsocks.json && \
		echo "	\"password\":\"$password\"," >> /etc/shadowsocks.json && \
		echo "	\"timeout\":300," >> /etc/shadowsocks.json && \
		echo "	\"method\":\"aes-256-cfb\"," >> /etc/shadowsocks.json && \
		echo "	\"fast_open\": false," >> /etc/shadowsocks.json && \
		echo "	\"workers\": 1" >> /etc/shadowsocks.json && \
		echo "}" >> /etc/shadowsocks.json
else
	echo "Config file found.."
fi

# start vnstat daemon, no matter if it is container restarting..
service vnstat restart

echo "Starting Service.."

 # To run SS in the foreground:
ssserver -c /etc/shadowsocks.json

# more about shadowsocks on wiki page: 
# https://github.com/shadowsocks/shadowsocks/wiki
## Shadowsocks Trouble Shooting / 关于使用SS的问题小结
这里会列举一些问题以及解决办法，用来给遇到问题的人/以后再次遇到的自己留作参考。

### Q: 在安装了锐速的环境中，Docker 停止了container之后，为什么会丢失与虚拟机的连接？/ Why the connection to VM lost when docker stopped a container in a environment with RuiSu?
A: 目前来看，是运行中的锐速导致的连接丢失，当把锐速停止了之后，连接又会恢复正常。所以，这时候建议可以先停止锐速，再去操作docker。/ Currently, as far as I know, it is because the running RuiSu service will somehow break the ssh connection between local host machine and the remote machine. So I think stop RuiSu before doing docker stuff is a choice here.
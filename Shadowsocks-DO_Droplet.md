## Shadowsocks Deploy & Digital Ocean Droplet
这篇文章会主要用来介绍自己搭建DO，以及部署Shadowsocks的过程。会逐渐补充完整。。

- 新建DO-Droplet
- 部署 Shadowsocks
    +   配置ENV环境
    +   非Docker直接安装使用
    +   安装 Docker
        *   制作 Docker Image
        *   运行 Docker SS   
-   连接测试
    +   测试手机端
    +   测试PC/MAC端
-   配置MAC端
    +   安装ShadowsocksX/GoAgent/SwitchyOmega
    +   配置ShadowsocksX/GoAgent
    +   配置SwitchyOmega
    +   连接测试
-   小结

### 新建DO Droplet
..

### 部署 Shadowsocks
配置SS的过程其实非常简单，[ShadowsocksWiki主页](https://github.com/shadowsocks/shadowsocks/wiki)可以看到安装和配置方法以及更准确的解释。 由于自己是部署在DO上，并且没有购买backup跟snapshot，为了使得环境尽可能干净，我选择用Docker来虚拟化出SS的运行环境，这样以后SS出现问题故障，重新启动一个新的SS也会变得十分简单。

#### 配置ENV环境

##### 非Docker直接安装使用

为了方便一些直接使用者的阅读，在这里给出配置SS的环境所需要的依赖：
```python
yum install m2crypto python-setuptools
easy_install pip
pip install shadowsocks
```
安装好SS Service之后，需要新建一个本地的配置文件来存储关键的配置信息：
```
# shadowsocks.json
{
    "server":"0.0.0.0",
    "server_port":8388,
    "local_address":"127.0.0.1",
    "local_port":1080,
    "password":"mypassword",
    "timeout":300,
    "method":"aes-256-cfb",
    "fast-open":false,
    "workers":1
}
```
对于每个变量的解释，如下所示：
>**server*: 该服务器的hostname或者IP(IPv4/IPv6)， 默认是"0.0.0.0"。
**server_port**: 该服务器会开放的端口号，默认是8388。
**local_address**： 本地IP地址，默认设定为127.0.0.1。
**local_port**： 本地开放的端口， 默认的本地端口为1080。
**password**： 连接所需要的密码。（used to encrypt transfer.）
**timeout**: 超时时长(按秒计)
**method**： 加密方式。 默认是"table", 推荐的是"aes-256-gcm"/"aes-256-cfb"。
**fast-open**： TCP Fast Open. 用来在减少延迟， 默认"false"。
**workers**： 产生子进程的数量，默认是1。

之后，就可以通过如下命令来启动SS Service:
```python
# read config file to start
ssserver -c /path/to/shadowsocks.json

# stop service
ssserver -d stop

# check logs
less /var/log/shadowsocks.log

```

更多命令可以查看SS的[Github Page](https://github.com/shadowsocks/shadowsocks/blob/master/README.md).

#### 安装Docker
由于原本的安装配置就不太复杂，所以使用Docker在这里也很简单。
下面的内容主要是自行制作Docker Image， 以及如何运行这个Docker Image。 由于Image的设计制作可以多种多样，所以运行的方法也都各异。

P.S.所有制作的源文件都附件在了文末。
更多关于Docker的用法，可以参考[Docker Doc](https://docs.docker.com/)。
##### 制作Docker Image

##### 运行Docker Image


### 连接测试

#### 手机端


#### PC/MAC端


### 配置MAC端

#### 安装SS/GA&SwitchyOmega

#### 配置SS/GA

#### 配置SO

#### 连接测试


### 小结




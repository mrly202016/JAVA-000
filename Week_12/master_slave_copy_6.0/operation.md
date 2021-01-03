### 启动3个服务

```shell script
redis-server master_6379.conf
redis-server slave_6380.conf
redis-server slave_6381.conf
```
  
### redis-cli -p 6379/6380/6381，登录后通过 info 命令查看到以下信息即为成功

```shell script
role:slave
master_host:127.0.0.1
master_port:6379
```
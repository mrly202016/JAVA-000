### 启动3个cluster服务

```shell script
redis-server cluster_6379.conf
redis-server cluster_6380.conf
redis-server cluster_6381.conf
```
  
### 执行创建集群命令

```shell script
redis-trib.rb create --replicas 1 127.0.0.1:6379 127.0.0.1:6380 127.0.0.1:6381
```

### 查看集群状态

```shell script
redis-trib.rb check 127.0.0.1:6379

Connecting to node 127.0.0.1:6379: OK
Connecting to node 127.0.0.1:6380: OK
Connecting to node 127.0.0.1:6381: OK
```

### 连接到任一集群ip开始操作

```shell script
redis-cli -c -h 127.0.0.1 -p 6380
```
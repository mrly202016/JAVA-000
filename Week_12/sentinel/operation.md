### 在前面主从服务启动的情况下，启动2个哨兵服务

```shell script
redis-sentinel sentinel_7379.conf
redis-sentinel sentinel_7380.conf
```
  
### 关闭port:6379服务，查看6380和6381的info主从变化

##### (1)6379是主，关闭6379前，6380和6381的info都是

```shell script
role:slave
master_host:127.0.0.1
master_port:6379
```

 
##### (2)关闭6379后，6380变为主，6381的info是

```shell script
role:slave
master_host:127.0.0.1
master_port:6380
```

##### (2)重新启动6379，6379的info是

```shell script
role:slave
master_host:127.0.0.1
master_port:6380
```

6379也变为了6380的从
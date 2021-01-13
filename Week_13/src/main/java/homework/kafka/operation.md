### 启动3个kafka

```shell script
./bin/kafka-server-start.sh server9091.properties 
./bin/kafka-server-start.sh server9092.properties 
./bin/kafka-server-start.sh server9093.properties
```

### 创建带有副本的topic

```shell script
bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic testTopic --partitions 3 --replication-factor 2
```

### 发送消息

```shell script
bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic testTopic
>test1
>test2
```

### 消费消息

```shell script
bin/kafka-console-consumer.sh --bootstrap-server localhost:9091 --topic testTopic --from-beginning
test1
test2
```

### 执行性能测试:

```shell script
bin/kafka-producer-perf-test.sh --topic testTopic --num-records 100000 --record-size 1000 --throughput 2000 --producer-props bootstrap.servers=localhost:9092
bin/kafka-consumer-perf-test.sh --bootstrap-server localhost:9092 --topic testTopic -- fetch-size 1048576 --messages 100000 --threads 1
```
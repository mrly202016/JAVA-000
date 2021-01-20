# 学习总结

本周学习了 RabbitMQ、RocketMQ、Pulsar 等消息中间件，了解了未来消息中间件的发展趋势，并且学习了消息中间件的整合框架 EIP/Camel/Spring Integration

### 作业设计思路（第二个版本:自定义Queue）

* 自定义一个 ArrayQueueWrapper 对象，里面包装了一个范型数组，用来保存消息，这里是 TmqMessage 
* ArrayQueueWrapper 里面用 writeIndex 保存 producer 的最新写入位置
* 每个 TmqConsumer 用 offset 保存各自的消息消费偏移位置，每次 poll 消息的时候都根据当前 offset 拉取消息
* TmqConsumer 拉取消息如果不为空或者没有出现异常，则在处理完业务逻辑之后，手动提交确认消息的 offset，将 offset 加1，否则不手动提交确认

关键实现在 ArrayQueueWrapper ，TmqConsumer 手动提交消息的demo 在 TmqDemo 中
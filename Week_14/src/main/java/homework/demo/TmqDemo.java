package homework.demo;

import lombok.SneakyThrows;
import homework.tmq.TmqBroker;
import homework.tmq.TmqConsumer;
import homework.tmq.TmqMessage;
import homework.tmq.TmqProducer;

public class TmqDemo {

    @SneakyThrows
    public static void main(String[] args) {

        String topic = "tmq.test";
        TmqBroker broker = new TmqBroker();
        broker.createTopic(topic);

        TmqConsumer consumer = broker.createConsumer();
        consumer.subscribe(topic);
        final boolean[] flag = new boolean[1];
        flag[0] = true;
        new Thread(() -> {
            while (flag[0]) {
                try{
                    TmqMessage<Order> message = consumer.poll(100);
                    if(null != message) {
                        System.out.println("consumer:"+message.getBody());
                        //这里手动提交 offset
                        consumer.ack();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println("程序退出。");
        }).start();

        TmqProducer producer = broker.createProducer();
        for (int i = 0; i < 1000; i++) {
            Order order = new Order(1000L + i, System.currentTimeMillis(), "USD2CNY", 6.51d);
            producer.send(topic, new TmqMessage(null, order));
        }
        Thread.sleep(500);
        System.out.println("点击任何键，发送一条消息；点击q或e，退出程序。");
        while (true) {
            char c = (char) System.in.read();
            if(c > 20) {
                System.out.println(c);
                producer.send(topic, new TmqMessage(null, new Order(100000L + c, System.currentTimeMillis(), "USD2CNY", 6.52d)));
            }

            if( c == 'q' || c == 'e') break;
        }

        flag[0] = false;

    }
}

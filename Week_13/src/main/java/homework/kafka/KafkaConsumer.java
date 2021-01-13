package homework.kafka;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;


/**
 *
 */
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"testTopic"})
    public void consume(ConsumerRecord<?, ?> record){
        System.out.println("message："+record.value());
    }

}

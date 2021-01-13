package homework.kafka;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.kafka.core.KafkaTemplate;

/**
 *
 */
@RestController
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
​
    @GetMapping("/test/send")
    public void send() {
        kafkaTemplate.send("testTopic", "testhehe");
    }
}

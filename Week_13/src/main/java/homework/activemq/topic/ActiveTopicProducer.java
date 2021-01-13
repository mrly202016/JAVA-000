package homework.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 *
 */
public class ActiveTopicProducer {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createTopic("testTopic");

        MessageProducer producer = session.createProducer(destination);
        for (int i = 1 ; i <= 10 ; i++){
            TextMessage message = session.createTextMessage("第" + i + "个test-topic文本消息");
            producer.send(message);
            System.out.println("test-topic已发送的消息：" + message.getText());
        }
        connection.close();
    }
}

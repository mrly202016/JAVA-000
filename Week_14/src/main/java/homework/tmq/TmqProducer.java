package homework.tmq;

public class TmqProducer {

    private TmqBroker broker;

    public TmqProducer(TmqBroker broker) {
        this.broker = broker;
    }

    public boolean send(String topic, TmqMessage message) {
        Tmq tmq = this.broker.findKmq(topic);
        if (null == tmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
        return tmq.send(message);
    }
}

package homework.tmq;

public class TmqConsumer<T> {

    private final TmqBroker broker;

    private Tmq tmq;

    volatile int offset;

    public TmqConsumer(TmqBroker broker) {
        this.broker = broker;
    }

    public void subscribe(String topic) {
        this.tmq = this.broker.findKmq(topic);
        this.offset=0;
        if (null == tmq) throw new RuntimeException("Topic[" + topic + "] doesn't exist.");
    }

    public TmqMessage<T> poll(long timeout) {
        return tmq.poll(offset,timeout);
    }

    public void ack() {
        offset++;
    }

}

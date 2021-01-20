package homework.tmq;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TmqBroker { // Broker+Connection

    public static final int CAPACITY = 10000;

    private final Map<String, Tmq> kmqMap = new ConcurrentHashMap<>(64);

    public void createTopic(String name){
        kmqMap.putIfAbsent(name, new Tmq(name,CAPACITY));
    }

    public Tmq findKmq(String topic) {
        return this.kmqMap.get(topic);
    }

    public TmqProducer createProducer() {
        return new TmqProducer(this);
    }

    public TmqConsumer createConsumer() {
        return new TmqConsumer(this);
    }

}

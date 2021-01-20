package homework.tmq;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

public final class Tmq {

    public Tmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new ArrayQueueWrapper(TmqMessage.class, capacity);
    }

    private String topic;

    private int capacity;

    private ArrayQueueWrapper<TmqMessage> queue;

    public boolean send(TmqMessage message) {
        return queue.offer(message);
    }

    public TmqMessage poll(int offset) {
        return queue.poll(offset);
    }

    @SneakyThrows
    public TmqMessage poll(int offset, long timeout) {
        return queue.poll(offset, timeout, TimeUnit.MILLISECONDS);
    }

}

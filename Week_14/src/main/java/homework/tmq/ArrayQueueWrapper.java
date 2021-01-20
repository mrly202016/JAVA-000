package homework.tmq;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class ArrayQueueWrapper<T> {
    T[] arr;
    int capacity;
    volatile int writeIndex;
    final ReentrantLock putLock = new ReentrantLock();
    final ReentrantLock takeLock = new ReentrantLock();
    final Condition notEmpty = takeLock.newCondition();

    public ArrayQueueWrapper(Class<T> tmqClass, int capacity) {
        this.capacity = capacity;
        arr = (T[]) Array.newInstance(tmqClass, capacity);
    }

    public boolean offer(T message) {
        putLock.lock();
        try {
            if (writeIndex + 1 > arr.length - 1) {
                capacity <<= 1;
                arr = Arrays.copyOf(arr, capacity);
            }
            arr[writeIndex] = message;
            writeIndex++;
            signalNotEmpty();
        } finally {
            putLock.unlock();
        }
        return true;
    }

    public T poll(int offset) {
        if (offset < writeIndex) return arr[offset];
        return null;
    }

    public T poll(int offset, long timeout, TimeUnit milliseconds) throws InterruptedException {
        T x = null;
        long nanos = milliseconds.toNanos(timeout);
        takeLock.lockInterruptibly();
        try {
            while (offset >= writeIndex) {
                if (nanos <= 0)
                    return poll(offset);
                nanos = notEmpty.awaitNanos(nanos);
            }
            x = poll(offset);
            notEmpty.signalAll();
        }finally {
            takeLock.unlock();
        }
        return x;
    }

    void signalNotEmpty() {
        takeLock.lock();
        try {
            notEmpty.signalAll();
        } finally {
            takeLock.unlock();
        }
    }

}

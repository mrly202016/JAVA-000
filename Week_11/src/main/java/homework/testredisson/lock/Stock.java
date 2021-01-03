package homework.testredisson.lock;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 */
public class Stock {
    static AtomicInteger side=new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        RedissonClient redisson=RedisClient.getRedisClient();
        RAtomicLong count=redisson.getAtomicLong("20210103");
        int value=100;
        count.set(value);
        Thread t1= new Thread(() -> {
            while (true){
                if(count.decrementAndGet()>=0){
                    side.incrementAndGet();
                    System.out.println("扣减成功");
                }else {
                    System.out.println("扣减失败");
                }
            }
        });
        Thread t2= new Thread(() -> {
            while (true){
                if(count.decrementAndGet()>=0){
                    side.incrementAndGet();
                    System.out.println("扣减成功");
                }else {
                    System.out.println("扣减失败");
                }
            }
        });
        t1.start();
        t2.start();
        Thread.sleep(10000);
        System.out.println(count.get()<0);
        System.out.println(side.get()==value);
    }
}

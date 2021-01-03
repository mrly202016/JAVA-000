package homework.testredisson.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        RedissonClient redisson=RedisClient.getRedisClient();
        RLock lock=redisson.getLock("20200102Lock");
        long start=System.currentTimeMillis();
        boolean flag=lock.tryLock(1,5,TimeUnit.SECONDS);
        long end=System.currentTimeMillis();
        System.out.println("Demo1 cost:"+(end-start)/1000);
        if(flag){
            try{
                System.out.println("is held by current thread:"+lock.isHeldByCurrentThread());
                System.out.println("try flag:"+flag+",lock name:"+lock.getName()+",is locked?:"+lock.isLocked());
                while (true){
                    Thread.sleep(1000);
                    System.out.println("Demo1 remain time:"+lock.remainTimeToLive());
                }
            }finally {
                lock.unlock();
            }
        }
        System.out.println("Demo1 没抢到锁");
    }
}

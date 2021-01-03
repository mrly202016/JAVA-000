package homework.testredisson.lock;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        RedissonClient redisson=RedisClient.getRedisClient();
        RLock lock=redisson.getLock("20200102Lock");
        long start=System.currentTimeMillis();
        System.out.println("is held by current thread:"+lock.isHeldByCurrentThread());
        boolean flag=lock.tryLock(15,TimeUnit.SECONDS);
        long end=System.currentTimeMillis();
        System.out.println("cost:"+(end-start)/1000);
        if(flag){
            System.out.println("try flag:"+flag+",lock name:"+lock.getName()+",is locked?:"+lock.isLocked());
            System.out.println("*******************************");
            try{
                while (true){
                    Thread.sleep(2000);
                    System.out.println("Demo2 remain time:"+lock.remainTimeToLive());
                }
            }finally {
                lock.unlock();
            }
        }
        System.out.println("Demo2没抢到锁");
    }
}

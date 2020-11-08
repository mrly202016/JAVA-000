package homework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/8
 * Time: 13:11
 */
public class Five {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long start=System.currentTimeMillis();

        CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
        Thread t= new Thread(() -> {
            Result.result=sum();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        t.start();

        cyclicBarrier.await();
        System.out.println("异步计算结果为："+ Result.result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class Result{
        static int result;
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}

package homework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/8
 * Time: 13:35
 */
public class Six {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<Integer> future=executor.submit(() -> sum());
        executor.shutdown();

        System.out.println("异步计算结果为："+ future.get());

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

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

package homework;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/8
 * Time: 13:11
 */
public class Two {
    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        Thread t= new Thread(() -> Result.result=sum());
        t.start();

        t.join();
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

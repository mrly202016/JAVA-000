package homework;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/8
 * Time: 13:11
 */
public class One {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();

        Thread t= new Thread(() -> Result.result=sum());
        t.start();

        while(Result.result==0);
        System.out.println("异步计算结果为："+ Result.result);

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    static class Result{
        static volatile int result;
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

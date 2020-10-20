package demo.jvm0104;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/10/18
 * Time: 22:43
 */
public class LocalVariableTest {
    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        int num1 = 1;
        int num2 = 2;
        ma.submit(num1);
        ma.submit(num2);
        double avg = ma.getAvg();
    }
}

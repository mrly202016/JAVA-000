package demo.jvm0104;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/10/18
 * Time: 22:41
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.0D;

    public void submit(double value){
        this.count ++;
        this.sum += value;
    }
    public double getAvg(){
        if(0 == this.count){ return sum;} return this.sum/this.count;
    }

}

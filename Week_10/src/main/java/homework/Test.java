package homework;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

/**
 *
 */
public class Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Test test=new Test();
        test.test();
    }

    void test() throws IllegalAccessException, InstantiationException {
        Demo demo = new ByteBuddy()
                .subclass(Demo.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(TestAop.class))
                .make()
                .load(getClass().getClassLoader())
                .getLoaded()
                .newInstance();
        demo.test1("t-1");
        demo.test2("t-2");
    }
}

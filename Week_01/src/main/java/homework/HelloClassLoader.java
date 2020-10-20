package homework;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/10/19
 * Time: 22:38
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            Class clazz=new HelloClassLoader().findClass("Hello.xlass");
            Method hello=clazz.getDeclaredMethod("hello");
            hello.invoke(clazz.newInstance());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream is=null;
        try{
            is=getResourceAsStream(name);
            byte[] bytes=new byte[is.available()];
            is.read(bytes);
            for(int i=0;i<bytes.length;i++) bytes[i]= (byte) (bytes[i]^0xff);
            is.close();
            return defineClass(name.substring(0,name.indexOf(".")),bytes,0,bytes.length);
        }catch (Exception e){
            return null;
        }
    }

}

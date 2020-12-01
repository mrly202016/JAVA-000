package homework.datasource.util;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/30
 * Time: 17:32
 */
public class DataSourceContext {
    private static ThreadLocal<String> local=new ThreadLocal<>();

    public static void setDataSource(String dbType){
        local.set(dbType);
    }

    public static String getDataSource(){
        return local.get();
    }

    public static void clear(){
        local.remove();
    }
}

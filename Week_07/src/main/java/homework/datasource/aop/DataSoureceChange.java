package homework.datasource.aop;

import homework.datasource.util.DataSourceTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/12/1
 * Time: 10:26
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSoureceChange {
    DataSourceTypeEnum type() default DataSourceTypeEnum.MASTER;
}

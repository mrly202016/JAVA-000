package homework.datasource.aop;

import homework.datasource.util.DataSourceContext;
import homework.datasource.util.DataSourceEnum;
import homework.datasource.util.DataSourceTypeEnum;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/12/1
 * Time: 09:29
 */
@Aspect
@Component
public class DaoAspect {
    @Pointcut(value="execution(* com.example.testdatasource.datasource.dao.mapper.*.*(..))")
    public void daoPoint(){}

    @Before(value="daoPoint()&&@annotation(dataSoureceChange)")
    public void before(DataSoureceChange dataSoureceChange){
        if(dataSoureceChange.type()== DataSourceTypeEnum.MASTER){
            DataSourceContext.setDataSource(DataSourceEnum.PRIMARY.getValue());
            return;
        }

        DataSourceEnum dataSourceEnum=DataSourceEnum.getRandomSlaveEnum();
        DataSourceContext.setDataSource(dataSourceEnum.getValue());
    }

    @After(value="daoPoint()")
    public void after(){
        DataSourceContext.clear();
    }
}

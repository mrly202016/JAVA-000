package homework.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import homework.datasource.DynamicDataSource;
import homework.datasource.util.DataSourceEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/30
 * Time: 17:43
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource masterDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slaveone")
    public DataSource slaveOneDataSource() {
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slavetwo")
    public DataSource slaveTwoDataSource() {
        return new DruidDataSource();
    }

    @Bean
    public AbstractRoutingDataSource routingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,@Qualifier("slaveOneDataSource") DataSource slaveOneDataSource,@Qualifier("slaveTwoDataSource") DataSource slaveTwoDataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceEnum.PRIMARY.getValue(), masterDataSource);
        targetDataSources.put(DataSourceEnum.SECONDARY.getValue(), slaveOneDataSource);
        targetDataSources.put(DataSourceEnum.TERTIARY.getValue(), slaveTwoDataSource);

        AbstractRoutingDataSource routingDataSource = new DynamicDataSource();
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(masterDataSource);
        return routingDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("routingDataSource") DataSource routingDataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(routingDataSource);
        return bean.getObject();
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("routingDataSource") DataSource routingDataSource) {
        return new DataSourceTransactionManager(routingDataSource);
    }
}

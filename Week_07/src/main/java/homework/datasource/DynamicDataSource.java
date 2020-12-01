package homework.datasource;

import homework.datasource.util.DataSourceContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/30
 * Time: 17:36
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContext.getDataSource();
    }
}

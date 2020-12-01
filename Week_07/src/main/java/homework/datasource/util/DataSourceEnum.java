package homework.datasource.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/30
 * Time: 17:22
 */
public enum DataSourceEnum {
    PRIMARY("master"),SECONDARY("slave1"),TERTIARY("slave2");
    static final List<DataSourceEnum> list=new ArrayList<>();

    static {
        list.add(SECONDARY);
        list.add(TERTIARY);
    }

    private DataSourceEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public static DataSourceEnum getRandomSlaveEnum() {
        int next= ThreadLocalRandom.current().nextInt(0,list.size());
        return list.get(next);
    }
}

package homework.datasource.dao.mapper;

import homework.datasource.aop.DataSoureceChange;
import homework.datasource.dao.domain.User;
import homework.datasource.util.DataSourceTypeEnum;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IntelliJ IDEA
 * User: yyzz
 * Date: 2020/11/30
 * Time: 19:52
 */
public interface UserMapper {
    @DataSoureceChange(type = DataSourceTypeEnum.SLAVE)
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "userName",  column = "user_name"),
            @Result(property = "password", column = "password")
    })
    User getOne(Long id);

    @DataSoureceChange(type = DataSourceTypeEnum.MASTER)
    @Insert("INSERT INTO user(user_name,password) VALUES(#{userName}, #{password})")
    void insert(User user);
}

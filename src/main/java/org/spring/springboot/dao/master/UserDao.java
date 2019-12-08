package org.spring.springboot.dao.master;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.spring.springboot.domain.PushUserInfo;
import org.spring.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface UserDao {
    


    /**
     * 根据用户名获取用户信息
     *
     * @param userName
     * @return
     */
    User findByName(@Param("userName") String userName);

  void saveUser(@Param("user") User user,@Param("tableName") String tableName);

  List<User> getUserCursor();

    /**
     * 根据用户uid获取用户信息
     */
    @Select("select *   from ${tableName}  where uid = ${uid}")
    PushUserInfo getUserById(String tableName, Long uid);

}

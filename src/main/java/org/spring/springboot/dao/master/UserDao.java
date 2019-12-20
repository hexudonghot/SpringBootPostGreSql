package org.spring.springboot.dao.master;

import org.apache.ibatis.annotations.*;
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



    @Update("<script> " +
            "update public.user set " +
            "<if test='userName!=null  and userName!=\"\"  '> user_name=#{userName} ,</if>" +
            "<if test='description!=null  and  description!=\"\"  '> description=#{description}, </if>" +
            "<if test='datas!=null'> datas=#{datas,typeHandler=org.spring.springboot.hand.JSONTypeHandlerPg},</if> " +
            "id = #{id} where id = #{id} " +
            "</script>")

//    @Update("UPDATE t_user SET gmt_modified = now(), user_name = #{userName} WHERE id = #{id}")
    public int update(User user);

}

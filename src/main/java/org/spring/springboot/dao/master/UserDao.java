package org.spring.springboot.dao.master;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.User;

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

  @Insert("INSERT INTO public.user(id,user_name,description,datas) VALUES(#{id}, #{userName}, #{description}, #{datas,typeHandler=org.spring.springboot.hand.JSONTypeHandlerPg})")
  void saveUser(User user);
}

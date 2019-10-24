package org.spring.springboot.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Getter
@Setter
public class User  implements RowMapper<User>, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8823504831198719837L;
    /**
     * 城市编号
     */
    private Long id;

    /**
     * 城市名称
     */
    private String userName;
    /**
     * 描述
     */
    private String description;
    private Object datas;
    private City city;


    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {

        User userInfo = new User();
        userInfo.setId(rs.getLong("id"));
        userInfo.setDescription(rs.getString("description"));
        userInfo.setDatas(rs.getString("datas"));
        userInfo.setUserName(rs.getString("user_name"));
        return userInfo;
    }
}

package org.spring.springboot.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户实体类
 *
 * Created by bysocket on 07/02/2017.
 */
@Getter
@Setter
public class User {

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


}

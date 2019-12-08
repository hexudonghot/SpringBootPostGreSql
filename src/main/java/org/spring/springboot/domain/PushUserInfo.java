package org.spring.springboot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 */

@Getter
@Setter
//@Builder
public class PushUserInfo implements RowMapper<PushUserInfo>,  Serializable
{
    private Long uid;
    private Long lastTime;
    private String pushChannals;
    private Integer gender;
    private String language;
    private Long updateTime;
    private String avatar;
    private String userName;
    private String upliveCode;
    private String qualityAuthor;
    private String setting;
    private String mobilePhone;
    private Integer feature;
    private Long createTime;
    private String countryCode;
    private Integer grade;
    private String location;
    private Integer badge;
    private String version;
    private String distance;
    private Integer userType;


    @Override
    public String toString() {
        return "{" +
                "uid=" + uid +
                ", pushChannals='" + pushChannals + '\'' +
                ", language='" + language + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userName='" + userName + '\'' +
                ", setting='" + setting + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", grade=" + grade +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public PushUserInfo mapRow(ResultSet rs, int i) throws SQLException {
        PushUserInfo userInfo = new PushUserInfo();
        userInfo.setUid(rs.getLong("uid"));
        userInfo.setLastTime(rs.getLong("last_time"));
        userInfo.setPushChannals(rs.getString("push_channals"));
        userInfo.setGender(rs.getInt("gender"));
        userInfo.setLanguage(rs.getString("language"));
        userInfo.setUpdateTime(rs.getLong("update_time"));
        userInfo.setAvatar(rs.getString("avatar"));
        userInfo.setUserName(rs.getString("user_name"));
        userInfo.setUpliveCode(rs.getString("uplive_code"));
        userInfo.setSetting(rs.getString("setting"));
        userInfo.setMobilePhone(rs.getString("mobile_phone"));
        userInfo.setFeature(rs.getInt("feature"));
        userInfo.setCreateTime(rs.getLong("create_time"));
        userInfo.setCountryCode(rs.getString("country_code"));
        userInfo.setGrade(rs.getInt("grade"));
        userInfo.setLocation(rs.getString("location"));
        userInfo.setBadge(rs.getInt("badge"));
        userInfo.setVersion(rs.getString("version"));
        userInfo.setDistance(rs.getString("distance"));
        userInfo.setUserType(rs.getInt("user_type"));
        return userInfo;
    }

    public PushUserInfo() {
        this.uid = 0L;
        this.lastTime = null;
        this.pushChannals = "{}";
        this.gender = null;
        this.language = "";
        this.updateTime = null;
        this.avatar = "";
        this.userName = "";
        this.upliveCode = "";
        this.qualityAuthor = "";
        this.setting = "{}";
        this.mobilePhone = "";
        this.feature = null;
        this.createTime = null;
        this.countryCode = "";
        this.grade = null;
        this.location = "{}";
        this.badge = null;
        this.version = "";
        this.distance = "";
        this.userType = null;
    }
}

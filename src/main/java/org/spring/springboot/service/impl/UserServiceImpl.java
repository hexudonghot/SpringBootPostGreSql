package org.spring.springboot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.domain.PushUserInfo;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源
    @Autowired
    @Qualifier("clusterJdbcTemplate")
    JdbcTemplate jdbcTemplate;










    @Override
    public User findByName(String userName) {

  AtomicInteger i= new AtomicInteger();
        Long t1  = System.currentTimeMillis();
            jdbcTemplate.query(con -> {
                con.setAutoCommit(false);
                PreparedStatement preparedStatement =
                        con.prepareStatement("select * from up_user_info",
                                ResultSet.TYPE_FORWARD_ONLY,
                                ResultSet.CONCUR_READ_ONLY);
                preparedStatement.setFetchSize(1000);
                preparedStatement.setFetchDirection(ResultSet.FETCH_FORWARD);
                return preparedStatement;
            }, rs -> {
                while (rs.next()) {
                     System.err.println(rs.getString("uid"));
                     i.getAndIncrement();
                }
            });

        Long t2  = System.currentTimeMillis();
        System.out.println(t2-t1);
        System.out.println(i);

        return null;
    }

    @Override
    public void saveUser(User user,String u) {
         userDao.saveUser(user,u);
    }

    @Override
    public void getUserCursor()
    {
        List<User> employees = userDao.getUserCursor();
        Iterator<User> iter = employees.iterator();
        List<User> smallChunk = new ArrayList(10);
        while (iter.hasNext())
        {


        }
    }

    @Override
    public PushUserInfo getUserById(String tableName, Long uid) {
        return userDao.getUserById("game_user_info",10001537L);
    }
}

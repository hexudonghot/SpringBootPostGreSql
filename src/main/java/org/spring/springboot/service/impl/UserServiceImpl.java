package org.spring.springboot.service.impl;

import org.apache.ibatis.cursor.Cursor;
import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 用户业务实现层
 *
 * Created by bysocket on 07/02/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao; // 主数据源

    @Autowired
    private CityDao cityDao; // 从数据源
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public User findByName(String userName) {
        User user = userDao.findByName(userName);
        TransactionSynchronizationManager.initSynchronization();
        DataSource dataSource = jdbcTemplate.getDataSource();
        Connection connection = DataSourceUtils.getConnection(dataSource);
        try {
            connection.setAutoCommit(false);
            String cusorName="cursor_user_" + System.currentTimeMillis();
            StringBuffer ss  =new StringBuffer();
            ss.append(" declare ");
            ss.append(cusorName);
            ss.append(" cursor  for select * from  public.user");
            ss.append(" where 1=1 ");
            String  fetchSql = "FETCH " + 1000 + " from  "+cusorName ;
            jdbcTemplate.execute(ss.toString());
                  List<User> list =     jdbcTemplate.query(fetchSql,new User());
                  while (list.size()>0)
                  {
                      list =     jdbcTemplate.query(fetchSql,new User());
                        System.out.println(list);
                  }

            connection.commit();
        } catch (SQLException e) {
        } finally {
            try {
                TransactionSynchronizationManager.clearSynchronization();
            } catch (IllegalStateException e) {
            }
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
            }
        }


        return user;
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
}

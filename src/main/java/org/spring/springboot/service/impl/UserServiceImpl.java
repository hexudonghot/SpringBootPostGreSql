package org.spring.springboot.service.impl;

import org.apache.ibatis.cursor.Cursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.dao.cluster.CityDao;
import org.spring.springboot.dao.master.UserDao;
import org.spring.springboot.domain.City;
import org.spring.springboot.domain.PushUserInfo;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
            String  fetchSql = "FETCH " + 10000 + " from  "+cusorName ;
            Long t1 = System.currentTimeMillis();
            jdbcTemplate.execute(ss.toString());
                  List<User> list =     jdbcTemplate.query(fetchSql,new User());
                  int i=0;
                  while (list.size()>0)
                  {
                      logger.info("第几页：" +i++);
                      list =     jdbcTemplate.query(fetchSql,new User());
                       if(list.size()>0)
                           System.out.println(list.get(0).getId());
                  }
            Long t2 = System.currentTimeMillis();
            logger.info("执行时间：" + (t2-t1));
            jdbcTemplate.execute("close  " + cusorName );
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

    @Override
    public PushUserInfo getUserById(String tableName, Long uid) {
        return userDao.getUserById("game_user_info",10001537L);
    }
}

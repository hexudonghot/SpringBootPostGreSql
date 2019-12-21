package org.spring.springboot.service.impl;

import com.alibaba.fastjson.JSON;
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

import java.sql.*;
import java.util.*;
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
        Connection conn = null;
        Statement stmt = null;
        try {
            conn=  jdbcTemplate.getDataSource().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.FETCH_FORWARD);
            stmt.setFetchSize(10);
            ResultSet rs = stmt.executeQuery("select uid from  public.xft_user_info");//选择import java.sql.ResultSet;
                while(rs.next()){//如果对象中有数据，就会循环打印出来
                System.out.println(rs.getLong("uid"));
                    }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
                try {
                    conn.setAutoCommit(true);
                    stmt.close();;
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
        }
        /**
         List<Long> uids = new ArrayList<>();
         AtomicInteger indexv= new AtomicInteger();
         String tableName = configService.getConf(req.getAppId()).getTableName();
         StringBuffer ss  =new StringBuffer();
         ss.append("select uid from  public."+tableName);
         ss.append(" where 1=1 ");
         QueryParam  query = req.getQuery();
         if (query != null)
         {
         if(!query.getLanguage().equals(""))
         ss.append(" and  language= '").append(query.getLanguage()+"'");
         if(!query.getCountry().equals(""))
         ss.append(" and  country_code= '").append(query.getCountry()+"'");
         }
         logger.info("sql-----"  +ss);
         PreparedStatement ps = null;
         ResultSet rs = null;
         Connection con = null;
         try {
         con=jdbcTemplate.getDataSource().getConnection();
         con.setAutoCommit(false);
         ps = con.prepareStatement(String.valueOf(ss),
         //                    ResultSet.TYPE_FORWARD_ONLY,
         //                    ResultSet.CONCUR_READ_ONLY,
         //                    ResultSet.HOLD_CURSORS_OVER_COMMIT
         ResultSet.TYPE_FORWARD_ONLY,
         ResultSet.CONCUR_READ_ONLY,
         ResultSet.HOLD_CURSORS_OVER_COMMIT);

         } catch (SQLException e) {
         e.printStackTrace();
         }
         try {
         ps.setFetchSize(100);
         rs = ps.executeQuery();
         logger.info("rs    " );
         while (rs.next())
         {
         logger.info("while------------------------------------111");
         long uid = rs.getLong("uid");
         if(uids.size()  >=100)
         {

         logger.info("while------------------------------------2222");
         Map<String,String> mp = new HashMap<>();
         mp.put("follows",JSON.toJSONString(uids));
         mp.put("req",JSON.toJSONString(req));
         kafkaTemplate.send(TopicUtil.GROUP_SEND_MQ_RESEND,JSON.toJSONString(mp));
         uids.clear();
         }
         else
         uids.add(uid);
         logger.info("当前第                          " +indexv.getAndIncrement()     + "        页");
         }
         if(uids.size()>0)
         {
         Map<String,String> mp = new HashMap<>();
         mp.put("follows",JSON.toJSONString(uids));
         mp.put("req",JSON.toJSONString(req));
         kafkaTemplate.send(TopicUtil.GROUP_SEND_MQ_RESEND,JSON.toJSONString(mp));
         }

         logger.info("while------------------------------------3333333333");
         con.commit();
         } catch (SQLException e)
         {
         e.printStackTrace();
         }
         finally
         {
         try
         {
         rs.close();
         ps.close();
         con.close();
         }
         catch (SQLException e)
         {
         e.printStackTrace();
         }
         }
         */



    }

    @Override
    public PushUserInfo getUserById(String tableName, Long uid) {
        return userDao.getUserById("game_user_info",10001537L);
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}

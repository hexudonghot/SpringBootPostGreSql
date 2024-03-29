package org.spring.springboot.controller;


import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @return
     */
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public void findByName() {
       userService.findByName("");
    }

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @return
     */
    @RequestMapping(value = "/api/usersave", method = RequestMethod.GET)
    public void saveUser() {
        for(int i=0;i<10000000;i++)
        {
            User user  = new User();
            user.setDescription("2");
            user.setId(System.currentTimeMillis());
            user.setDatas("{\"dd\":2}");
            userService.saveUser(user,"public.user");
        }
    }


    @RequestMapping(value = "/api/getUserCursor", method = RequestMethod.GET)
    public void getUserCursor() {
//        while (true)
//        {
//            try
//            {
//                Thread.sleep(3000L);
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//            System.out.println(userService.getUserById("",1L).getSetting());
//        }

        userService.getUserCursor();
    }
    @RequestMapping(value = "/api/kafka", method = RequestMethod.GET)
    public void kafka() {

       while (true)
           kafkaTemplate.send("hxd","data");
    }



    @RequestMapping(value = "/api/updateUser", method = RequestMethod.GET)
    public void updateUser() {
        User user = new User();
        user.setId(1L);
        user.setDescription("fads/\''");
        user.setDatas("{}");
        userService.updateUser(user);
    }

}

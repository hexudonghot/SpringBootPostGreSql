package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public User findByName(@RequestParam(value = "userName", required = true) String userName) {
        return userService.findByName(userName);
    }

    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @return
     */
    @RequestMapping(value = "/api/usersave", method = RequestMethod.GET)
    public void saveUser() {
        User user  = new User();
        user.setDescription("2");
        user.setId(System.currentTimeMillis());
        user.setDatas("{\"dd\":2}");
        userService.saveUser(user);
    }

}

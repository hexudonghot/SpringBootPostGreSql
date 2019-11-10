package org.spring.springboot.controller;


import com.google.common.util.concurrent.RateLimiter;
import org.spring.springboot.domain.User;
import org.spring.springboot.service.UserService;
import org.spring.springboot.util.ThreadPoolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用户控制层
 *
 * Created by bysocket on 07/02/2017.
 */
@RestController
public class ThreadController {
    private RateLimiter rateLimiter = RateLimiter.create(5);
    static final int SIZE=2*1024*1024;
    /**
     * 根据用户名获取用户信息，包括从库的地址信息
     *
     * @return
     */
    @RequestMapping(value = "/thread", method = RequestMethod.GET)
    public void thread() {


        rateLimiter.acquire();
        System.out.println("1111111111111111");
        ThreadPoolUtil.executor(()-> this.gets());

    }

    private void gets()
    {
        int[] i = new int[SIZE];
        System.out.println(i);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        int[] i = new int[SIZE];
        System.out.println(i);
    }


}

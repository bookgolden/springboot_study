package com.java.redis.controller;

import com.java.redis.bean.User;
import com.java.redis.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class PoolController {

    @Autowired
    private PoolService poolService;

    @RequestMapping("/getUserList.action")
    public List<User> getUserList() throws ExecutionException, InterruptedException {
        return poolService.getUser();
    }

}

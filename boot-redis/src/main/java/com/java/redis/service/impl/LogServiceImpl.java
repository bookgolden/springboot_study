package com.java.redis.service.impl;

import com.java.redis.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServiceImpl implements LogService {

    String CONS_KEY = "users";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String getName() {
        log.info("===bob_java");
        return "bob_java";
    }


}

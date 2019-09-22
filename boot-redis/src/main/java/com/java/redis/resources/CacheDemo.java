package com.java.redis.resources;

import com.java.redis.bean.User;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Callable<User>() {
                @Override
                public User call() throws Exception {
                    return null;
                }
            });
        }
    }

}

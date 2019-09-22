package com.java.redis.service.impl;

import com.java.redis.bean.User;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class UserCallable implements Callable<User> {

    private int iNum;
    private CountDownLatch countDownLatch;

    public UserCallable(int iNum, CountDownLatch countDownLatch) {
        this.iNum = iNum;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public User call() throws Exception {
        try {
            return null;// getUserById(iNum);
        }finally {
            countDownLatch.countDown();
        }
    }

//    User getUserById(int iNum){
//        try {
//            TimeUnit.SECONDS.sleep(new Random().nextInt(4));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return new User("hello_"+iNum, "00"+iNum);
//    }
}

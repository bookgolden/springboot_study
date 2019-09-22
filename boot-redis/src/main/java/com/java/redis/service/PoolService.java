package com.java.redis.service;

import com.java.redis.bean.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface PoolService {

    List<User> getUser() throws ExecutionException, InterruptedException;

}

package com.java.redis.service.impl;

import com.java.redis.bean.User;
import com.java.redis.service.PoolService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@Service("poolService")
public class PoolServiceImpl implements PoolService {

    @Override
    public List<User> getUser() throws ExecutionException, InterruptedException {
        return get();
    }

    List<User> get() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<User>> list = new ArrayList<>();
        final CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Future<User> future = executorService.submit(new UserCallable(i, latch));
            list.add(future);
        }

        latch.await();

        executorService.shutdown();

        List<User> userList = new ArrayList<>();
        for (Future<User> future : list) {
            User user = future.get();
            userList.add(user);
        }
        log.info("=result= {}", userList);
        return userList;
    }


}
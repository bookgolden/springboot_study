package com.java.redis.service.impl;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@Slf4j
public class HttpJsonRequest {

    public static void main(String[] args) throws InterruptedException {
        log.info("version = {}", Runtime.getRuntime());
        int parallism = Runtime.getRuntime().availableProcessors();
        log.info("core = {}", parallism);
        Map<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        map.put("d", "3");
        map.put("e", "4");
        map.put("f", "5");
        map.put("g", "6");

        CountDownLatch countDownLatch = new CountDownLatch(map.size());
        PrintTask printTaskDemo = new PrintTask(0, map.size(), countDownLatch, map);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(printTaskDemo);
        countDownLatch.await();
        forkJoinPool.shutdown();
    }

}

@Slf4j
class PrintTask extends RecursiveAction {

    private static final int THRESHOLD = 2;
    private int start;
    private int end;
    private CountDownLatch countDownLatch;
    private Map<String, String> map;

    public PrintTask(int start, int end, CountDownLatch countDownLatch, Map<String, String> map) {
        this.start = start;
        this.end = end;
        this.countDownLatch = countDownLatch;
        this.map = map;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                log.info("线程 = {}, key = {}, value = {}", Thread.currentThread().getName(), entry.getKey(), entry.getValue());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                countDownLatch.countDown();
//            }
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        } else {
            int mid = (start + end) / 2;
            PrintTask left = new PrintTask(start, mid, countDownLatch, map);
            PrintTask right = new PrintTask(mid, end, countDownLatch, map);
            left.fork();
            right.fork();
        }
    }
}

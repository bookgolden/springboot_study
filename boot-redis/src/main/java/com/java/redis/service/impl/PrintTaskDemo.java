package com.java.redis.service.impl;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

public class PrintTaskDemo extends RecursiveAction {

    private static final long serialVersionUID = 1L;
    private static final int INDEX = 50;
    private int start;
    private int end;

    public PrintTaskDemo(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.util.concurrent.RecursiveAction#compute()
     */
    @Override
    protected void compute() {
        if (end - start < INDEX) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "----" + i);
            }
        } else {
            int middle = (end + start) / 2;
            PrintTaskDemo taskLeft = new PrintTaskDemo(start, middle);
            PrintTaskDemo taskRight = new PrintTaskDemo(middle, end);
            //taskLeft.invoke();执行给定的任务，在完成后返回其结果。
            //并行执行两个“小任务”
/*			taskLeft.fork();
			taskRight.fork();*/
            invokeAll(taskLeft, taskRight);//执行给定的任务
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PrintTaskDemo task = new PrintTaskDemo(0, 300);
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(task);
        pool.awaitTermination(2, TimeUnit.SECONDS);//阻塞2秒
        pool.shutdown();
    }
}
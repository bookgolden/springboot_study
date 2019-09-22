package com.java.redis.resources;

public class TestInnClass {
    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    SingletonD2 singletonD2 = SingletonD2.getInstance();
//                    SingletonDemo singleton = SingletonDemo.getInstance();
                    System.out.println(singletonD2);
                }
            }).start();
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

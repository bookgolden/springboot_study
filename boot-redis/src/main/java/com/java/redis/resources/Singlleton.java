package com.java.redis.resources;

public class Singlleton {

    private static volatile Singlleton singleton;

    private Singlleton() {
    }

    public static Singlleton getInstance() {
        try {
            if (null != singleton) {
                synchronized (Singlleton.class) {
                    if (null != singleton) {
                        singleton = new Singlleton();
                    }
                }
            }
        } catch (Exception e) {
        }
        return singleton;
    }
}

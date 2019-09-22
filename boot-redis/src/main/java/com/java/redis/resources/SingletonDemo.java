package com.java.redis.resources;

public class SingletonDemo {

    private static volatile SingletonDemo instance;

    static {
        instance = new SingletonDemo();
    }

    public static SingletonDemo getInstance() {
        return instance;
    }

}

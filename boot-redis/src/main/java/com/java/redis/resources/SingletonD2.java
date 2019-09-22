package com.java.redis.resources;

public class SingletonD2 {

    private SingletonD2() {
    }

    private static class InnerClass {
        private static SingletonD2 singletonD2 = new SingletonD2();
    }

    public static SingletonD2 getInstance() {
        return InnerClass.singletonD2;
    }
}

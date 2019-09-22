package com.java.redis.facotry;

import com.java.redis.service.LamdaBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class BuilderFactory {

    public static <T extends LamdaBuilder> T create(Class<T> clazz, String objName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Constructor constructor = clazz.getConstructor(String.class);
        return (T) constructor.newInstance(objName);
    }

}

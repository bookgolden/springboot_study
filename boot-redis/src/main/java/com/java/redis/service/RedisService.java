package com.java.redis.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface RedisService {

    void saveMap(String key);

    List<String> getByKey(String key, String field01, String field02) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

    boolean delKey(String key);

}

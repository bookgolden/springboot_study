package com.java.redis.facotry;

import com.java.redis.service.LamdaBuilder;
import com.java.redis.service.NormalBuilder;

public class LamdaFactory extends CacheFactory {

    @Override
    LamdaBuilder lamdaBuilder() {
        return null;
    }

    @Override
    NormalBuilder normalBuilder() {
        return null;
    }
}

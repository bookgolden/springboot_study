package com.java.redis.facotry;

import com.java.redis.service.LamdaBuilder;
import com.java.redis.service.NormalBuilder;

public abstract class CacheFactory {

    abstract LamdaBuilder lamdaBuilder();

    abstract NormalBuilder normalBuilder();

}

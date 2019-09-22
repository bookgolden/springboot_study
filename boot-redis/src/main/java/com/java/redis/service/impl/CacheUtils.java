package com.java.redis.service.impl;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


public class CacheUtils {

    @Setter
    @Getter
    private static Map<String, Map<String, String>> dataMap = new HashMap<>();
    @Setter
    @Getter
    private static Map<String, Map<String, String>> indexMap = new HashMap<>();


}

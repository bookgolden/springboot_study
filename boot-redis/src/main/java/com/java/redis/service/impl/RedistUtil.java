package com.java.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Setter
@Getter
public class RedistUtil {

    private ObjectMapper objectMapper = new ObjectMapper();

    private Stream<String> keyStream;

    private Map<String, String> dataMap;

    private Class clazz;

    public Stream<String> eq(String key){
        keyStream.filter(x->key.equals(x));
        return keyStream;
    }

    public void getByField(String key){
        keyStream.filter(x -> key.equals(x));
        setKeyStream(keyStream);
    }

    public <T> T get(Class<T> clazz){
        List<String> keyList = keyStream.collect(Collectors.toList());
        List<String> resultList = dataMap.entrySet().stream().filter(x->{
            return keyList.contains(x.getKey());
        }).map(w->{
            return w.getValue();
        }).collect(Collectors.toList());

        try {
            T t = objectMapper.readValue(resultList.toString(), clazz);
            log.info("t = {}", JSON.toJSON(t));
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    public <T> T getObjByKey(Class<T> clazz, String key, String value) {
        try {
            T t = objectMapper.readValue(value, clazz);
            log.info("t = {}", JSON.toJSON(t));
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getList(Class<T> clazz, String key, String value) throws IOException {
//        List<T> list = JSON.parseArray(value, clazz);
        List<T> list = objectMapper.readValue(value, List.class);
        log.info("list = {}", JSON.toJSON(list));
        return list;
    }


}

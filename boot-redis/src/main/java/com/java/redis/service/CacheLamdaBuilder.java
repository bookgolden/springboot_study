package com.java.redis.service;

import com.java.redis.service.impl.CacheUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Setter
@Getter
public class CacheLamdaBuilder implements LamdaBuilder {

    private Set<String> keySet = new HashSet<>();
    private String objName;
    private Map<String, String> dataMap = new HashMap<>();
    private Map<String, String> keyMap = new HashMap<>(); //<keyValue, fieldValue>

    public CacheLamdaBuilder(String objName) {
        this.objName = objName;
        dataMap = CacheUtils.getDataMap().get(objName);
    }

    public LamdaBuilder query(String objName){
        this.objName = objName;
        dataMap = CacheUtils.getDataMap().get(objName);
        return this;
    }

    @Override
    public LamdaBuilder byKey(String key, String fieldValue) {

        return this;
    }

    @Override
    public LamdaBuilder eq(String fieldName, String fieldValue) {
        Map<String, String> map = CacheUtils.getIndexMap().get(fieldName);
        Set<String> fieldItemSet = map.entrySet().stream().filter(x -> {
            return fieldValue.equals(x.getValue());
        }).map(x -> x.getKey()).collect(Collectors.toCollection(HashSet::new));
        this.mergeKey(fieldItemSet);
        return this;
    }

    public void mergeKey(Set<String> itemKeySet) {
        if (CollectionUtils.isEmpty(keySet)) {
            keySet.addAll(itemKeySet);
            return;
        }
        keySet.retainAll(itemKeySet);
    }

    @Override
    public LamdaBuilder like(String fieldName, String fieldValue) {
        Map<String, String> map = CacheUtils.getIndexMap().get(fieldName);
        Set<String> fieldItemSet = map.entrySet().stream().filter(x -> {
            return x.getValue().contains(fieldValue);
        }).map(x -> x.getKey()).collect(Collectors.toSet());
        this.mergeKey(fieldItemSet);
        return this;
    }

    @Override
    public LamdaBuilder start(String fieldName, String fieldValue) {

        return this;
    }

    @Override
    public LamdaBuilder end(String fieldName, String fieldValue) {
        return this;
    }

    @Override
    public LamdaBuilder between(String sField, String startVal, String eField, String endVal) {
        return this;
    }

    @Override
    public List<String> getResult() {
        dataMap = CacheUtils.getDataMap().get(objName);
        List<String> list = dataMap.entrySet().stream().filter(x -> keySet.contains(x.getKey())).map(w -> w.getValue()).collect(Collectors.toList());
        log.info("list = {}", list.toString());
        return list;
    }

}

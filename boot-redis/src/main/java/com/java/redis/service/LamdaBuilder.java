package com.java.redis.service;

import java.util.List;

public interface LamdaBuilder<T> {

    LamdaBuilder byKey(String key, String fieldValue);

    LamdaBuilder eq(String fieldName, String fieldValue);

    LamdaBuilder like(String fieldName, String fieldValue);

    LamdaBuilder start(String fieldName, String fieldValue);

    LamdaBuilder end(String fieldName, String fieldValue);

    LamdaBuilder between(String sField, String startVal, String eField, String endVal);

    List<String> getResult();

}

package com.java.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.redis.bean.UserBean;
import com.java.redis.facotry.BuilderFactory;
import com.java.redis.service.CacheLamdaBuilder;
import com.java.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private String objName = "userBean";
    private String addrName = "address";
    private String name = "name";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BuilderFactory builderFactory;

    @Override
    public void saveMap(String key) {
        CenterContext centerContext = new CenterContext();
        List<UserBean> list = centerContext.getUserList();
        Map<String, String> map = list.stream().collect(Collectors.toMap(x -> String.valueOf(x.getId()), x -> JSON.toJSONString(x)));
        redisTemplate.opsForHash().putAll(key, map);
    }

    @Override
    public List<String> getByKey(String key, String field01, String field02) throws IOException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Map<String, String> dataMap = redisTemplate.opsForHash().entries(key);

        List<String> list = dataMap.entrySet().stream().map(x->x.getValue()).collect(Collectors.toList());

//        RedistUtil redistUtil = new RedistUtil();
//        redistUtil.eq("15");
//        log.info("= {}", JSON.toJSON(userBean));
//        UserBean userBean = getObjByKey(UserBean.class, "", list.get(0));
        List<UserBean> vsList = getList(UserBean.class, list.toString());

        Map<String, String> addrIndexMap = vsList.stream().collect(Collectors.toMap(x->String.valueOf(x.getId()), x->x.getAddress(), (key1, key2) -> key2));
        Map<String, String> nameIndexMap = vsList.stream().collect(Collectors.toMap(x->String.valueOf(x.getId()), x->x.getName(), (key1, key2) -> key2));

        CacheUtils.getIndexMap().put("address", addrIndexMap);
        CacheUtils.getIndexMap().put("name", nameIndexMap);
        CacheUtils.getDataMap().put(objName, dataMap);

//        List<String> resultList = new CacheLamdaBuilder(objName).like(addrName, field01).eq(name, field02).getResult();

        CacheLamdaBuilder cacheLamdaBuilder = BuilderFactory.create(CacheLamdaBuilder.class, objName);
        List<String> resultList = cacheLamdaBuilder.query(objName).like(addrName, field01).eq(name, field02).getResult();

        log.info("size = {}", resultList.size());
        log.info("vsList = {}", JSON.toJSON(resultList));
        return resultList;
    }

    @Override
    public boolean delKey(String key) {
        return redisTemplate.delete(key);
    }

    public <T> T getObjByKey(Class<T> clazz, String value) {
        return (T)JSON.parseObject(value, clazz);
//        try {
//            T t = objectMapper.readValue(value, clazz);
//            log.info("t = {}", JSON.toJSON(t));
//            return t;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public <T> List<T> getList(Class<T> clazz, String value) {
        List<T> list = JSON.parseArray(value, clazz);
//        List<T> list = objectMapper.readValue(value, List.class);
        log.info("list = {}", JSON.toJSON(list));
        return list;
    }

}

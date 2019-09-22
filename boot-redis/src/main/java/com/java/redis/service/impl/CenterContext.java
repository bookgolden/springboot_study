package com.java.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.java.redis.bean.User;
import com.java.redis.bean.UserBean;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CenterContext {

    private static List<UserBean> userList;

    public List<UserBean> getUserList() {
        userList = null;
        System.gc();

        userList = new ArrayList<>();

        long beginTime = System.currentTimeMillis();
        log.info("数据初始化-开始时间={}", beginTime);
        for (int i = 1; i < 32; i++) {
            UserBean userBean = new UserBean();
            userBean.setId(i);
            userBean.setName("java_oracle" + i);
            userBean.setAddress("中国河北省邢台市巨鹿县观寨乡刘庄村_" + i);
            userBean.setAlias("中国边远乡村_" + i);
            userBean.setAge(100 + i);
            userBean.setHegiht(180 + 2 * i);
            userBean.setMoney(new BigDecimal(100 * i));
            userBean.setKilometer(Long.valueOf(1000 + i));
            userBean.setFar(Long.valueOf(200 + 2 * i));
            userBean.setWay(Long.valueOf(200 + i * 10));
            userBean.setSalary(new BigDecimal(20000 + i));
            userBean.setWifeSalary(new BigDecimal(20000 + i));
            userBean.setTotalSalary(new BigDecimal(50000 + i));
            userBean.setCompany("京东集团-数字科技");
            userBean.setCountry("zhongGuo");
            userBean.setProvince("河北省");
            userBean.setCity("邢台市");
            userBean.setCounty("巨鹿县");
            userBean.setVillage("刘庄村");
            userBean.setSchoolName("刘庄大学");
            userBean.setGrade(4);
            userList.add(userBean);
        }
        long endTime = System.currentTimeMillis();
        log.info("数据初始化-结束时间 = {}", endTime);
        log.info("数据初始化-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);
//        log.info("=数据初始化={}", objectMapper.writeValueAsString(userList));
        return userList;
    }

    static Map<String, String> getMap() {
        User user = new User(1, "bob", "123");
        User user1 = new User(2, "java", "456");
        User user2 = new User(3, "oracle", "789");
        Map<String, String> map = new HashMap<>();
        map.put(String.valueOf(user.getId()), JSON.toJSONString(user));
        map.put(String.valueOf(user1.getId()), JSON.toJSONString(user1));
        map.put(String.valueOf(user2.getId()), JSON.toJSONString(user2));

        log.info("{}", user);
        log.info("{}", user1);
        log.info("{}", user2);

        return map;
    }

}

package com.java.redis.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.redis.bean.User;
import com.java.redis.bean.UserBean;
import com.java.redis.service.LogService;
import com.java.redis.service.RedisService;
import com.java.redis.util.DataModel;
import com.java.redis.util.ReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class RedisController implements InitializingBean {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private RedisTemplate redisTemplate;

    private static List<UserBean> userList;

    @Autowired
    private LogService logService;
    @Autowired
    private RedisService redisService;


//    @Autowired
//    public RedisController(RedisTemplate redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    //    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @RequestMapping("/testMock.action")
    public String testMock(){
        return "testMock";
    }

    @RequestMapping("/getUser.action")
    public User getUser(String name, String phoneNum){
        log.info("getUser() == name = {} , phoneNum = {}", name, phoneNum);

        User user = new User();
        user.setPhoneNum("123456");
        user.setName("bob");

        return user;
    }

    @RequestMapping("/getRediss.action")
    public DataModel<String> getRediss(String flag, Integer sex) {
//        return ReturnUtil.exception(CacheErrorEnum.ID_CARD_EXPIRED);
        return ReturnUtil.successResult();
    }

    @RequestMapping("/delKey.action")
    public boolean delKey(String key) {
        return redisService.delKey(key);
    }

    @RequestMapping("/saveMap.action")
    public void save(String key) {
        redisService.saveMap(key);
    }

    @RequestMapping("/getByKey.action")
    public List<String> getRedis(String key, String flag, String field01, String field02) throws Exception {
        String name = logService.getName();
        log.info(" name = {}", name);
        List<String> map = redisService.getByKey(key, field01, field02);
        log.info(" key = {}", map);
//        throw new BusinessException(CacheErrorEnum.ID_CARD_EXPIRED);

        return map;
//        String key = "";
//        if ("clear".equals(flag)) {
//            afterPropertiesSet();
//        } else if ("map".equals(flag)) {
//            key = "user:hash:one";
//            putAllAsMap(key);
//
////            key = "user:hash:batch";
////            perBatchHash(key);
//
////            key = "user:hash:ervery";
////            everyHash(key);
//        } else if("list".equals(flag)){
//            key = "user:list:one";
//            putAllAsOneObjectString(key);
//        } else if("string".equals(flag)){
//            key = "hash:user:bean";
//        } else if("every".equals(flag)){
//            key = "hash:user:bean";
//
//        }

//        log.info("== {}", userList.toString());

//        redisClientTemplate.setToRedis("kv", "bob");
//        System.out.println(redisClientTemplate.getRedis("kv"));
//        redisTemplate.opsForValue().set("kv", "kkkkk");
//        redisTemplate.opsForHash().put("hash:key1", "id01", "ob_hash_v1");
//        Object obj = redisTemplate.opsForHash().get("hash:key1", "id01");
//        System.out.println(obj);
//        String vs = (String)redisTemplate.opsForValue().get("kv");
//        System.out.println(vs);
//        return null;
    }

    public void putAllAsMap(String hkey) throws JsonProcessingException {
//        Map<String, String> map = new HashMap<>();
//        Map<Integer, Employee> ep = employee.stream().collect(Collectors.toMap(Employee::getAge, Function.identity()));
//        Map<Integer, UserBean> map =  userList.stream().collect(Collectors.toMap(UserBean::getId, Function.identity()));
//        return accounts.stream().collect(Collectors.toMap(Account::getId, Account::getUsername))

        long beginTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-开始时间 = {}", beginTime);

        Map<String, String> map = userList.stream().collect(Collectors.toMap((x)->{return String.valueOf(x.getId());}, UserBean::toString));
//        Map<String, UserBean> map =  userList.stream().collect(Collectors.toMap((x)->{return String.valueOf(x.getId());}, Function.identity()));

        for (int i = 0; i < userList.size(); i++) {
            UserBean userBean = userList.get(i);
            map.put(String.valueOf(userBean.getId()), objectMapper.writeValueAsString(userBean));
        }

//        long beginTime = System.currentTimeMillis();
//        log.info("redis-SET-数据存放-开始时间 = {}", beginTime);

//        redisTemplate.opsForHash().putAll(hkey, map);

        long endTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-结束时间 = {}, key = {}", endTime, hkey);
        log.info("redis-SET-数据存放-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);

        log.info("--------------------");

//        beginTime = System.currentTimeMillis();
//        log.info("redis-GET-数据获取-开始时间 = {}", beginTime);
////        List<Object> vlist = (List<Object>)redisTemplate.opsForHash().values(hkey);
//        Map<Object, Object> maps = redisTemplate.opsForHash().entries(hkey);
//        log.info("===== {} ", maps);
////        Map<String, String> result = (Map<String, String>)redisTemplate.opsForHash().values(hkey);
//        endTime = System.currentTimeMillis();
//        log.info("redis-GET-数据获取-结束时间 = {}", endTime);
//        log.info("redis-GET-数据获取-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);

//        objectMapper.writeValueAsString(vlist);

//        log.info(" vlist = {}", objectMapper.writeValueAsString(vlist));
//        for(Object obj : vlist){
//            UserBean userBean = (UserBean)obj;
//            log.info(" UserBean = {} ", objectMapper.writeValueAsString(obj));
//        }
//        for (Map.Entry entry: result.entrySet()){
//            UserBean userBean = (UserBean) entry.getKey();
//            log.info(objectMapper.writeValueAsString(userBean));
//        }
        log.info("======================================");
    }

    private void everyHash(String key) throws JsonProcessingException {
        long beginTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-开始时间 = {}, key = {}", beginTime, key);
        int count = 0;
        for(UserBean userBean: userList){
            count++;
//            log.info(" = {}", objectMapper.writeValueAsString(userBean));
            redisTemplate.opsForHash().put(key, userBean.getId(), objectMapper.writeValueAsString(userBean));
        }

        long endTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-结束时间 = {}, key = {}, count = {}", endTime, key, count);
        log.info("redis-SET-数据存放-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);

        log.info("--------------------");

        beginTime = System.currentTimeMillis();
        log.info("redis-GET-数据获取-开始时间 = {}", beginTime);

        Map<Object, Object> objMap = redisTemplate.opsForHash().entries(key);

        endTime = System.currentTimeMillis();
        log.info("redis-GET-数据获取-结束时间 = {}, key = {}", endTime, key);
        log.info("redis-GET-数据获取-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);
//        log.info(" list.toString = {}", objectMapper.writeValueAsString(objMap));

        log.info("======================================");
    }

    public void perBatchHash(String key) throws IOException {
        long beginTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-开始时间 = {}, key = {}", beginTime, key);

        Map<String, String> batchMap = new HashMap<>();

        int count = 0;
        for(int i=0; i< userList.size(); i++){
            UserBean userBean = userList.get(i);
            batchMap.put(String.valueOf(userBean.getId()), objectMapper.writeValueAsString(userBean));
            if (i % 70000 == 69999) {
                count++;
                log.info("cycle = {}", count);
                redisTemplate.opsForHash().putAll(key, batchMap);
                Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
//                log.info(" map = {}", objectMapper.writeValueAsString(map));
                batchMap = new HashMap<>();
            }
        }
//        redisTemplate.opsForHash().putAll(key, batchMap);

//        Map<Object, Object> resultMap = redisTemplate.opsForHash().entries(key);
//        log.info(" resultMap = {}", objectMapper.writeValueAsString(resultMap));

        long endTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-结束时间 = {}, key = {}", endTime, key);
        log.info("redis-SET-数据存放-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);

        log.info("======================================");
    }
    
    public void putAllAsOneObjectString(String key) throws IOException {
        long beginTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-开始时间 = {}, key = {}", beginTime, key);

        redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(userList));

        long endTime = System.currentTimeMillis();
        log.info("redis-SET-数据存放-结束时间 = {}, key = {}", endTime, key);
        log.info("redis-SET-数据存放-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);

        log.info("--------------------");

        beginTime = System.currentTimeMillis();
        log.info("redis-GET-数据获取-开始时间 = {}", beginTime);

        String objStr = (String) redisTemplate.opsForValue().get(key);
        List<UserBean> list = (List<UserBean>) objectMapper.readValue(objStr, UserBean.class);

        endTime = System.currentTimeMillis();
        log.info("redis-GET-数据获取-结束时间 = {}, key = {}", endTime, key);
        log.info("redis-GET-数据获取-总用时间 = {}, 秒数 = {}", (endTime - beginTime), (endTime - beginTime) / 1000);
        log.info(" list.toString = {}", objectMapper.writeValueAsString(list));

        log.info("======================================");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        userList = null;
        System.gc();

        userList = new ArrayList<>();

        long beginTime = System.currentTimeMillis();
        log.info("数据初始化-开始时间={}", beginTime);
        for (int i = 1; i < 20; i++) {
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
    }

    public void test() throws Exception {
        userList = null;
        System.gc();

        userList = new ArrayList<>();

        long beginTime = System.currentTimeMillis();
        log.info("数据初始化-开始时间={}", beginTime);
        for (int i = 1; i < 20; i++) {
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
    }

}

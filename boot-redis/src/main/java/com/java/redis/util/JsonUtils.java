package com.java.redis.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static JsonGenerator jsonGenerator = null;
    private static ObjectMapper objectMapper = new ObjectMapper();

    //    //1.1、JavaBean(Entity/Model)转换成JSON
//    public static void writeEntityJSON() throws IOException {
//        UserBean user = new User();
//        user.setUserName("bob");
//        user.setPassWord("bob123");
//        String str = objectMapper.writeValueAsString(user);
//        System.out.println(str);
//    }
//
//    //1.2、将Map集合转换成Json字符串
//    public static void writeMapJSON() throws JsonProcessingException {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("name", "HelloTitle");
//        map.put("account", "bean");
//        User user = new User();
//        user.setUserName("bob");
//        user.setPassWord("bob123");
//        map.put("user", user);
//        String str = objectMapper.writeValueAsString(map);
//        System.out.println(str);
//    }
//
//    //1.3、将list集合转换成json字符串
//    public static void writeListJSON() throws JsonProcessingException {
//        List<User> list = new ArrayList<User>();
//        User user = new User();
//        user.setUserName("bob");
//        user.setPassWord("bob123");
//
//        User user1 = new User();
//        user1.setUserName("admin");
//        user1.setPassWord("admin123");
//        list.add(user);
//        list.add(user1);
//        String str = objectMapper.writeValueAsString(list);
//        System.out.println(str);
//    }
//
//    //2.1、将json字符串转换成JavaBean对象
//    public static void readJson2Entity() throws JsonParseException, JsonMappingException, IOException {
//        String json = "{\"userName\":\"bob\",\"passWord\":\"bob123\"}";
//        User u = objectMapper.readValue(json, User.class);
//        System.out.println("userName=" + u.getUserName() + ",passWord=" + u.getPassWord());
//    }
//
//    //2.2、将json字符串转换成List<Map>集合
//    public static void readJson2List() throws JsonParseException, JsonMappingException, IOException {
//        String json = "[{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}," + "{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}]";
//        List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
//        for (int i = 0; i < list.size(); i++) {
//            Map<String, Object> map = list.get(i);
//            Set<String> set = map.keySet();
//            for (Iterator<String> it = set.iterator(); it.hasNext();) {
//                String key = it.next();
//                System.out.println(key + ":" + map.get(key));
//            }
//        }
//    }
//
//    //2.3、json字符串转换成Array
//    public static void readJson2Array() throws JsonParseException, JsonMappingException, IOException {
//        String json = "[{\"userName\":\"bob\",\"passWord\":\"bob123\"},{\"userName\":\"admin\",\"passWord\":\"admin123\"}]";
//        User[] users = objectMapper.readValue(json, User[].class);
//        for (User u : users) {
//            System.out.println("userName=" + u.getUserName() + ",passWord=" + u.getPassWord());
//        }
//    }
//
//    //2.4、json字符串转换Map集合
//    public static void readJson2Map() throws JsonParseException, JsonMappingException, IOException {
//        String json = "{\"success\":true,\"A\":{\"address\": \"address2\",\"name\":\"haha2\",\"id\":2,\"email\":\"email2\"}," + "\"B\":{\"address\":\"address\",\"name\":\"haha\",\"id\":1,\"email\":\"email\"}}";
//
//        Map<String, Map<String, Object>> map = objectMapper.readValue(json, Map.class);
//        // 遍历方式一
//        // for(Map.Entry entry:map.entrySet()){
//        // System.out.println("key()="+entry.getKey()+",value()="+entry.getValue());
//        // }
//        // 遍历方式二
//        Set<String> set = map.keySet();
//        Iterator<String> iter = set.iterator();
//        while (iter.hasNext()) {
//            String key = iter.next();
//            System.out.println("key=" + key + ", value=" + map.get(key));
//        }
//    }

}

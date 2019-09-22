package com.java.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.redis.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class CreateMock {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getString() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/testMock.action"))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("testMock"));
    }

    @Test
    public void post() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setPhoneNum("258963");
        user.setName("hawawa");
        mvc.perform(MockMvcRequestBuilders.post("/getUser.action")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(user)))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("phoneNum").value("123456"))
                .andExpect(jsonPath("name").value("bob"));
    }

    @Test
    public void getRediss() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        User user = new User();
        user.setPhoneNum("258963");
        user.setName("hawawa");

        RequestBuilder request = MockMvcRequestBuilders.post("/getRediss.action")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(user));

        mvc.perform(request)
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("result").value("success"));

        MvcResult mvcResult = mvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回结果：" + status);
        System.out.println(content);

    }


    @Test
    public void getOk() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/getOk.action")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("flag", "true").param("sex", "1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(content().string(Matchers.containsString("OK"))) //匹配返回值中的内容
//                .andExpect(jsonPath("$.errcode", is(0)));////使用jsonPath解析返回值，判断具体的内容 
        ;

    }

    @Test
    public void testInfo() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/getOk.action")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .param("userId", "11").param("userName", "henry");
//          .contentType(MediaType.APPLICATION_JSON)
//          .content(jsonstr).accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mvc.perform(request).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println("返回结果：" + status);
        System.out.println(content);

    }


    @Test
    public void girlList() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/getRediss.action").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"result\":\"success\",\"errorCode\":\"\",\"errorMsg\":\"\",\"data\":null}"));
        ;

    }


    @Test
    public void getHello() throws Exception{
        MvcResult mvcResult= mvc.perform(MockMvcRequestBuilders.get("/testMock.action")
                .param("name","lvgang")
                .accept(MediaType.TEXT_HTML_VALUE))
                // .andExpect(MockMvcResultMatchers.status().isOk())                    //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
//                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();                 //得到返回代码
        String content=mvcResult.getResponse().getContentAsString();    //得到返回结果

        log.info("===== status = {}, content = {}", status, content);

        Assert.assertEquals(200,status);                        //断言，判断返回代码是否正确
        Assert.assertEquals("testMock",content);                //断言，判断返回的值是否正确
    }



//    @Test
//    public void addGirl() throws Exception {
////        Girl girl = new Girl();
////        girl.setCupSize("B");
////        girl.setAge(19);
////        girl.setMoney(22.22);
//
//        //ObjectMapper 是一个可以重复使用的对象
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonString = "{\"cupSize\":\"B\", \"age\":19, \"money\":22.22}";
//        //将JSON字符串值转换成 Girl对象里的属性值
//        Girl girl = mapper.readValue(jsonString, Girl.class);
//        mvc.perform(MockMvcRequestBuilders.post("/girlsss")
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                /* 使用writeValueAsString() 方法来获取对象的JSON字符串表示 */
////                .content(mapper.writeValueAsString(girl)))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.cupSize").value("B"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.age").value(19))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.money").value(22.22));
//    }

}

package com.java.redis;

import com.java.redis.enums.ErrorEnum;
import com.java.redis.service.LogService;
import com.java.redis.service.impl.LogServiceImpl;
import com.java.redis.util.DataModel;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootRedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BootRedisApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private LogService logService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestRestTemplate restTemplate;

    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testMockMethod() {
        String name = logService.getName();
        log.info("== name = {}", name);
        LogService obj=mock(LogServiceImpl.class);
        when(obj.getName()).thenReturn("hello l4");
        String actual=obj.getName();
        assertEquals("bob_java",name);
        verify(obj).getName();
//        verify(obj,times(1)).getName(); //可以加参数验证次数
    }

    @Test
    public void testRest(){
        MultiValueMap map= new LinkedMultiValueMap();
        map.add("id","1");
        String response = restTemplate.postForObject("/testMock.action",null, String.class);
        log.info(" == {}", response);
        Assertions.assertThat(response).contains("hello");
        Assertions.assertThat(response).isGreaterThanOrEqualTo("hello");
    }

    @Test
    public void testModel(){
        DataModel<String> response = restTemplate.postForObject("/getRediss.action",null, DataModel.class);
        log.info(" == {}", response);
        Assertions.assertThat(response.getResult()).isGreaterThanOrEqualTo(ErrorEnum.SUCCESS);
    }

}

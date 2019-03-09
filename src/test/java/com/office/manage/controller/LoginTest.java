package com.office.manage.controller;

import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoginTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    UserMapper userMapper;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test//测试登录
    public void getUser()throws Exception{
        //发送用户信息json
        String json="{ \"user_name\":\"admin@office.com\", \"user_password\":\"123456\"}";
        //密码错误的json
        String pwjson = "{ \"user_name\":\"admin@office.com\", \"user_password\":\"ro123\"}";
        //账号错误的json
        String usjson = "{ \"user_name\":\"admin@offcom\", \"user_password\":\"ro123\"}";
        //正确登录
        mvc.perform(MockMvcRequestBuilders.post("/userLogin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("success"))

                .andDo(MockMvcResultHandlers.print());

        //密码错误登录
        mvc.perform(MockMvcRequestBuilders.post("/userLogin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(pwjson.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("登录失败"))
                .andDo(MockMvcResultHandlers.print());

        //账号错误登录
        mvc.perform(MockMvcRequestBuilders.post("/userLogin")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(usjson.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("登录失败"))
                .andDo(MockMvcResultHandlers.print());



    }
}
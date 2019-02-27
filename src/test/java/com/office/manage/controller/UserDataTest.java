package com.office.manage.controller;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserDataTest {

    @Autowired
    WebApplicationContext wac;


    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserList()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/allUsers?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/allUsers?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getSearchUserList()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/Search?s=")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/Search?s=d&start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void addUser()throws Exception {
        String json = "{ \"user_name\":\"testP\", \"user_password\":\"123456\"," +
                " \"user_truename\":\"test\", \"user_department\":\"ewre\", \"user_authority\":\"1\"}";
        mvc.perform(MockMvcRequestBuilders.post("/adduser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json1 = "{ \"user_name\":\"admin\", \"user_password\":\"123456\"," +
                " \"user_truename\":\"test\", \"user_department\":\"ewre\", \"user_authority\":\"1\"}";
        mvc.perform(MockMvcRequestBuilders.post("/adduser")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json1.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteUser()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.delete("/delete/mafeifei@office.com")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.delete("/delete/000")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void updateUser()throws Exception {
        String json = "{ \"user_name\":\"mafeifei@office.com\", \"user_password\":\"123456\"," +
                " \"user_truename\":\"test\", \"user_department\":\"ewre\", \"user_authority\":\"1\"}";
        mvc.perform(MockMvcRequestBuilders.put("/update/mafeifei@office.com")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json1 = "{ \"user_name\":\"mafeifei@office.com\", \"user_password\":\"123456\"," +
                " \"user_truename\":\"test\", \"user_department\":\"ewre\", \"user_authority\":\"1\"}";
        mvc.perform(MockMvcRequestBuilders.put("/update/mafeif")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json1.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());


    }
}
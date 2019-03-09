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
public class ApplyDataTest {

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
    public void addApply()throws Exception {
        String json = "{ \"apply_id\":\"16\", \"apply_user_id\":\"1\", \"apply_user_truename\":\"管理者\", " +
                "\"apply_user_name\":\"typecase\", \"apply_product_id\":\"3\", \"apply_product_name\":\"test\"" +
                ", \"apply_num\":\"5\", \"apply_product_price\":\"2\", \"apply_time\":\"2019-02-14\"" +
                ", \"apply_passman_id\":\"1\", \"apply_passman_name\":\"管理者\", \"apply_pass\":\"0\"}";
        mvc.perform(MockMvcRequestBuilders.post("/addApply")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json1 = "{ \"apply_id\":\"16\", \"apply_user_id\":\"1\", \"apply_user_truename\":\"管理者\", " +
                "\"apply_user_name\":\"typecase\", \"apply_product_id\":\"3\", \"apply_product_name\":\"test\"" +
                ", \"apply_num\":\"555\", \"apply_product_price\":\"2\", \"apply_time\":\"2019-02-14\"" +
                ", \"apply_passman_id\":\"1\", \"apply_passman_name\":\"管理者\", \"apply_pass\":\"0\"}";
        mvc.perform(MockMvcRequestBuilders.post("/addApply")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json1.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getAllApplyCheckList() throws Exception{
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/allApplyCheck?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/allApplyCheck?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllApplyList()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/allApplyList?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/allApplyList?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getSearchApplyCheck()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/SearchApplyCheck?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/SearchApplyCheck?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getSearchApplyList()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/SearchApplyList?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/SearchApplyList?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updataApplyCheckStatus()throws Exception {
        String json = "{ \"apply_id\":\"16\", \"apply_user_id\":\"1\", \"apply_user_truename\":\"管理者\", " +
                "\"apply_user_name\":\"typecase\", \"apply_product_id\":\"3\", \"apply_product_name\":\"test\"" +
                ", \"apply_num\":\"5\", \"apply_product_price\":\"2\", \"apply_time\":\"2019-02-14\"" +
                ", \"apply_passman_id\":\"1\", \"apply_passman_name\":\"管理者\", \"apply_pass\":\"1\"}";
        mvc.perform(MockMvcRequestBuilders.post("/sumbitApplyCheck")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json1 = "{ \"apply_id\":\"16\", \"apply_user_id\":\"1\", \"apply_user_truename\":\"管理者\", " +
                "\"apply_user_name\":\"typecase\", \"apply_product_id\":\"3\", \"apply_product_name\":\"test\"" +
                ", \"apply_num\":\"5\", \"apply_product_price\":\"2\", \"apply_time\":\"2019-02-14\"" +
                ", \"apply_passman_id\":\"1\", \"apply_passman_name\":\"管理者\", \"apply_pass\":\"3\"}";
        mvc.perform(MockMvcRequestBuilders.post("/sumbitApplyCheck")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json1.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void deleteApply()throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/deleteApply/16")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        //删除失败
        mvc.perform(MockMvcRequestBuilders.delete("/deleteApply/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());
    }
}
package com.office.manage.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
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
public class TypeDataTest {

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
    public void getType()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/TypeInfo?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/TypeInfo?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getTypeCategory()throws Exception {
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/typeCategory?start=1&category=办公耗材")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/typeCategory?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Rollback
    public void addNewProduct() throws Exception{
        String json = "{ \"type_category\":\"testP\", \"product_type\":\"alltest\"}";
        mvc.perform(MockMvcRequestBuilders.post("/addType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("成功添加类型"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @Rollback
    public void deleteProduct()throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/deleteType/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        //删除失败
        mvc.perform(MockMvcRequestBuilders.delete("/deleteType/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());
    }
}
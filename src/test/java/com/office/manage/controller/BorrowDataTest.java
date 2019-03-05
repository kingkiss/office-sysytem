package com.office.manage.controller;

import com.office.manage.domain.BorrowList;
import com.office.manage.domain.BorrowListMapper;
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

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BorrowDataTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    BorrowListMapper borrowListMapper;


    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllBorrowList()throws Exception {
        //获取成功
        session.setAttribute("user_id",1);
        session.setAttribute("user_authority",7);
        mvc.perform(MockMvcRequestBuilders.get("/allBorrowList?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/allBorrowList?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void getSearchBorrowList()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/SearchBorrowList?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/SearchBorrowList?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteBorrowList()throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/deleteBorrow/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        //删除失败
        mvc.perform(MockMvcRequestBuilders.delete("/deleteBorrow/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateBorrowList()throws Exception {
        BorrowList b = new BorrowList();
        b.setBorrwoinfo_product_price(3);
        String json2 = "{ \"borrowinfo_return\":\"2\", \"borrowinfo_return_num\":\"1\"," +
                " \"borrowinfo_missprice\":\"1\", \"borrowinfo_product_id\":\"2\", \"borrowinfo_id\":\"1\"}";
        //添加新物品成功
        mvc.perform(MockMvcRequestBuilders.post("/updateBorrow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json2.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json4 = "{ \"borrowinfo_return\":\"2\", \"borrowinfo_return_num\":\"1\"," +
                " \"borrowinfo_missprice\":\"1\", \"borrowinfo_product_id\":\"2\", \"borrowinfo_id\":\"0\"}";
        //添加新物品失败
        mvc.perform(MockMvcRequestBuilders.post("/updateBorrow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json4.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());



        String json1 = "{ \"borrowinfo_return\":\"1\", \"borrowinfo_return_num\":\"1\"," +
                " \"borrowinfo_missprice\":\"1\", \"borrowinfo_product_id\":\"2\", \"borrowinfo_id\":\"1\"}";

        mvc.perform(MockMvcRequestBuilders.post("/updateBorrow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json1.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        String json3 = "{ \"borrowinfo_return\":\"1\", \"borrowinfo_return_num\":\"1\"," +
                " \"borrowinfo_missprice\":\"1\", \"borrowinfo_product_id\":\"2\", \"borrowinfo_id\":\"0\"}";

        mvc.perform(MockMvcRequestBuilders.post("/updateBorrow")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json3.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());


    }
}
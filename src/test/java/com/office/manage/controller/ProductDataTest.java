package com.office.manage.controller;

import com.office.manage.domain.UserMapper;
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
public class ProductDataTest {

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

    @Test//获取所有物品
    public void getAllProductList() throws Exception{
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/allProduct?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //获取失败
        mvc.perform(MockMvcRequestBuilders.get("/allProduct?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test//获取所有类型
    public void getAllType() throws Exception{
        //获取成功
        mvc.perform(MockMvcRequestBuilders.get("/allType")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//搜索物品（用名称和类型，type是大的分类）
    public void getSearchProduct() throws Exception {
        //搜索成功
        mvc.perform(MockMvcRequestBuilders.get("/SearchProduct?start=1&product=文")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //搜索失败
        mvc.perform(MockMvcRequestBuilders.get("/SearchProduct?start=1&product=asud")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//获取文具类物品数据
    public void getStationeryProductList() throws Exception {
        //搜索成功
        mvc.perform(MockMvcRequestBuilders.get("/allStationery?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //搜索失败
        mvc.perform(MockMvcRequestBuilders.get("/allStationery?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//获取办公消耗品数据
    public void getConsumableProductList() throws Exception {
        //搜索成功
        mvc.perform(MockMvcRequestBuilders.get("/allConsumable?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //搜索失败
        mvc.perform(MockMvcRequestBuilders.get("/allConsumable?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//获取办公设备数据
    public void getEquipmentProductList() throws Exception{
        //搜索成功
        mvc.perform(MockMvcRequestBuilders.get("/allEquipment?start=1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //搜索失败
        mvc.perform(MockMvcRequestBuilders.get("/allEquipment?start=0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test//搜索物品（用名称和类型，type是大的分类）
    public void getSearchStationeryList() throws Exception {
        //搜索成功
        mvc.perform(MockMvcRequestBuilders.get("/SearchOffice?start=1&product=粉盒&type=办公耗材")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(true))
                .andDo(MockMvcResultHandlers.print());

        //搜索失败
        mvc.perform(MockMvcRequestBuilders.get("/allEquipment?start=0&product=复印纸&type=办公消耗品")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.testcase").value(false))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test//添加新物品
    @Rollback
    public void addNewProduct() throws Exception {
        String json = "{ \"product_name\":\"testP\", \"product_num\":\"333\", \"product_price\":\"111\", \"product_type\":\"typecase\"}";
        //添加新物品成功
        mvc.perform(MockMvcRequestBuilders.post("/addProduct")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("成功添加物品"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//删除物品
    @Rollback
    public void deleteProduct() throws Exception{
        mvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/121")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("已删除1个物品"))
                .andDo(MockMvcResultHandlers.print());

        //删除失败
        mvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("未知错误请重试"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test//修改物品信息
    @Rollback
    public void updateProduct() throws Exception {
        String json = "{ \"product_name\":\"testP\", \"product_num\":\"333\", \"product_price\":\"111\", \"product_type\":\"typecase\"}";
        //添加新物品成功
        mvc.perform(MockMvcRequestBuilders.put("/updateProduct/121")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("物品信息已修改！"))
                .andDo(MockMvcResultHandlers.print());

        //
        mvc.perform(MockMvcRequestBuilders.put("/updateProduct/0")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes()) //传json参数
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("未知错误"))
                .andDo(MockMvcResultHandlers.print());


    }




}
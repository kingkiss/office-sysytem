package com.office.manage.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
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



    }

    @Test
    public void getAllApplyCheckList() throws Exception{
    }

    @Test
    public void getAllApplyList()throws Exception {
    }

    @Test
    public void getSearchApplyCheck()throws Exception {
    }

    @Test
    public void getSearchApplyList()throws Exception {
    }

    @Test
    public void updataApplyCheckStatus()throws Exception {
    }

    @Test
    public void deleteApply()throws Exception {
    }
}
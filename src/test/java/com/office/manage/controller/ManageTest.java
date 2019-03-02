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
public class ManageTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mvc;
    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build(); //初始化MockMvc对象
        session = new MockHttpSession();
        session.setAttribute("user_id",1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void login() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/login")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void loginout() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/loginout"))
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_userInfo()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_userInfo")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_officelist()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_officelist")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_officeInfo() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/manage_officeInfo")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_officeType() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/manage_officeType")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_applyCheck()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_applyCheck")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_applyList()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_applyList")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_borrowList()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_borrowList")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_returnList()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_returnList")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void manage_useData()throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/manage_useData")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.view().name("manage"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("isUserInfo"))
                .andDo(MockMvcResultHandlers.print());
    }
}
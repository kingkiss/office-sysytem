package com.office.manage.controller;

import com.office.manage.ManageSystemApplication;
import com.office.manage.domain.UserMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ManageSystemApplication.class)
@Transactional
public class ManageDataTest {

    @Autowired
    WebApplicationContext wac;

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
    public void userInfo()throws Exception {
        session.setAttribute("user_id",1);
        session.setAttribute("user_name","test");
        session.setAttribute("user_password","test");
        session.setAttribute("user_truename","test");
        session.setAttribute("user_department","test");
        session.setAttribute("user_authority",1);
        session.setAttribute("user_phone","test");
        mvc.perform(MockMvcRequestBuilders.get("/userInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_name").value("test"))
                .andDo(MockMvcResultHandlers.print());

        MockHttpSession session2 = new MockHttpSession();
        mvc.perform(MockMvcRequestBuilders.get("/userInfo")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .session(session2)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.user_authority").value(4396))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void changeUserName() throws Exception{
        String json="{ \"user_truename\":\"OK\"}";
        session.setAttribute("user_name","admin@office.com");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserName")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("success"))
                .andDo(MockMvcResultHandlers.print());

        MockHttpSession session2 = new MockHttpSession();
        session2.setAttribute("user_name","mafeifei@ocom");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserName")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session2)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("fail"))
                .andDo(MockMvcResultHandlers.print());


    }

    @Test
    public void changeUserPhone()throws Exception {
        String json="{ \"user_phone\":\"1324124132\"}";
        session.setAttribute("user_name","admin@office.com");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserPhone")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("success"))
                .andDo(MockMvcResultHandlers.print());

        MockHttpSession session2 = new MockHttpSession();
        session2.setAttribute("user_name","mafeifei@ocom");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserPhone")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json.getBytes())
                .session(session2)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.info").value("fail"))
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void changeUserPwd()throws Exception {
        session.setAttribute("user_name","admin@office.com");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserPwd")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("userOldPwd","mff123")
                .param("userNewPwd","123456")
                .param("user_name","mafeifei@office.com")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(true))
                .andDo(MockMvcResultHandlers.print());

        session.setAttribute("user_name","admin@office.com");
        mvc.perform(MockMvcRequestBuilders.post("/changeUserPwd")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("userOldPwd","mff1")
                .param("userNewPwd","123456")
                .param("user_name","mafeifei@office.com")
                .session(session)
        )
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value(false))
                .andDo(MockMvcResultHandlers.print());

    }
}
package com.office.manage.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Rollback //验证新增用户和查询用户、搜索所有用户、删除用户
    public void testAddUserAndGetAllUserAndFindUserAndDeleteUser(){
        //添加一个用户
        int r = userMapper.addUser("testman","123456","test","12345678","test",1);
        //查询用户
        List<User> users = userMapper.getAllUser();
        //搜索用户
        User user = userMapper.findUser("testman");
        //验证是否插入
        Assert.assertEquals(1,r);
        //验证查询用户
        Assert.assertNotNull(users);
        //验证搜索用户
        Assert.assertEquals("testman",user.getUser_name());
        Assert.assertNotEquals(0,users.size());
        //删除用户
        int i = userMapper.deleteUser("testman");
        //验证删除用户是否成功
        Assert.assertEquals(1,i);
        Assert.assertNull(userMapper.findUser("testman"));
    }

    @Test
    @Rollback //修改用户信息
    public void testUpdateUserByName(){
        //添加一个用户
        int r = userMapper.addUser("testman","123456","test","12345678","test",1);
        //修改用户信息
        int result = userMapper.updateUserByName("changeTest","888888","test",1,"testman");
        User user = userMapper.findUser("testman");
        //验证是否修改成功
        Assert.assertEquals(1,result);
        Assert.assertEquals("changeTest",user.getUser_truename());
        Assert.assertEquals("888888",user.getUser_phone());
        Assert.assertEquals("test",user.getUser_department());
        //修改用户密码
        int p = userMapper.updateUserPwdByName("1111111","testman");
        user = userMapper.findUser("testman");
        Assert.assertEquals("1111111",user.getUser_password());
    }



    @After
    public void tearDown() throws Exception {
    }
}
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
public class TypeMapperTest {

    @Autowired
    TypeMapper typeMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Rollback //添加新类型、查询类型、删除类型
    public void testTypeFunction(){
        //添加类型
        int i = typeMapper.addNewType("testall","testman");
        //查询类型
        List<Typelist> types = typeMapper.getTypeInfoByCategory("testall");
        List<String> types1 = typeMapper.getAllType();
        //验证
        Assert.assertEquals(1,i);
        Assert.assertNotEquals(0,types1.size());
        Assert.assertEquals("testman",types.get(0).getProduct_type());
        //删除类型
        i = typeMapper.deleteType(String.valueOf(types.get(0).getType_id()));
        types = typeMapper.getTypeInfoByCategory("testall");
        //验证
        Assert.assertEquals(1,i);
        Assert.assertEquals(0,types.size());

    }

    @After
    public void tearDown() throws Exception {
    }
}
package com.office.manage.domain;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ApplyListMapperTest {

    @Autowired
    ApplyListMapper applyListMapper;

    @Before
    public void setUp() throws Exception {
        //创建一个申请记录实体ApplyList类

    }

    @Test
    @Rollback
    public void testAddProductApply() throws Exception{
        //插入一条申请数据并查询出来
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        //查询数据插入是否成功
        List<ApplyList> result = applyListMapper.getApplyCheckByUserNameAndProductName("test");
        //插入数据成功r=1
        Assert.assertEquals(1,r);
        //搜索刚刚查入的记录，刚刚插入的user_name为admintest
        Assert.assertEquals("admintest",result.get(0).getApply_user_name());
    }



    @After
    public void tearDown() throws Exception {
    }
}
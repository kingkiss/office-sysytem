package com.office.manage.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
public class BorrowListMapperTest {

    @Autowired
    BorrowListMapper borrowListMapper;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    @Rollback //借入记录的增删查
    public void testAddDeleteSearch(){
        //插入一条借入记录
        int i = borrowListMapper.addBorrowList(888,"test",1,"test1",1,1,"2019-2-13");
        //搜索记录
        List<BorrowList> borrowLists = borrowListMapper.getBorrowListById(888);
        List<BorrowList> borrowLists2 = borrowListMapper.getAllBorrowList();
        //验证插入查询
        Assert.assertEquals(1,i);
        Assert.assertEquals(1,borrowLists.size());
        Assert.assertNotEquals(0,borrowLists2.size());
        Assert.assertEquals("test",borrowLists.get(0).getBorrowinfo_user_truename());
        Assert.assertEquals("test1",borrowLists.get(0).getBorrowinfo_product_name());
        //模糊查询
        List<BorrowList> borrowLists1 = borrowListMapper.searchBorrowById("%te%");
        //验证模糊查询
        Assert.assertEquals(1,borrowLists1.size());
        Assert.assertEquals(888,borrowLists1.get(0).getBorrowinfo_user_id());
        Assert.assertEquals("test",borrowLists1.get(0).getBorrowinfo_user_truename());
        //删除记录
        int r = borrowListMapper.deleteBorrowById(borrowLists.get(0).getBorrowinfo_id());
        borrowLists = borrowListMapper.getBorrowListById(888);
        Assert.assertEquals(0,borrowLists.size());
    }



    @After
    public void tearDown() throws Exception {
    }
}
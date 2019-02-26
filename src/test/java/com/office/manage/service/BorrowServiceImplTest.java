package com.office.manage.service;

import com.office.manage.domain.BorrowList;
import com.office.manage.domain.BorrowListMapper;
import com.office.manage.domain.Product;
import com.office.manage.domain.ProductMapper;
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
public class BorrowServiceImplTest {
    @Autowired
    BorrowListMapper borrowListMapper;
    @Autowired
    BorrowService borrowService;
    @Autowired
    ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    @Rollback //借入记录挂失测试
    public void missBorrow() {
        int i = borrowListMapper.addBorrowList(888,"adminTest",1,"testP",2,2,"2019-1-1");
        List<BorrowList> borrowLists = borrowListMapper.getBorrowListById(888);
        BorrowList borrowList = borrowLists.get(0);
        try{
            boolean r = borrowService.missBorrow(borrowList);
            //验证借入
            Assert.assertTrue(r);
            BorrowList borrowList1 = new BorrowList();
            borrowList1.setBorrowinfo_id(888);
            r = borrowService.missBorrow(borrowList1);
            Assert.assertFalse(r);
        }catch (Exception e){
            e.printStackTrace();
        }



    }

    @Test
    @Rollback //归还
    public void returnBorrow() {
        //添加一个物品用于测试
        int i = productMapper.addNewProduct("testP",2,2,"fortest");
        //查询物品
        List<Product> products = productMapper.getProductFromAllProduct("testP");
        Product product = products.get(0);
        //添加申请
        int b = borrowListMapper.addBorrowList(888,"adminTest",product.getProduct_id(),"testP",1,1,"2019-1-1");
        List<BorrowList> borrowLists = borrowListMapper.getBorrowListById(888);
        BorrowList borrowList = borrowLists.get(0);
        borrowList.setBorrowinfo_return_num(1);
        try{
            boolean r = borrowService.returnBorrow(borrowList);
            //查询物品
            products = productMapper.getProductFromAllProduct("testP");
            product = products.get(0);
            Assert.assertTrue(r);
            Assert.assertEquals(3,product.getProduct_num());
            //不成功
            borrowList.setBorrowinfo_product_id(777);
            Assert.assertFalse(borrowService.returnBorrow(borrowList));
            borrowList.setBorrowinfo_id(666);
            Assert.assertFalse(borrowService.returnBorrow(borrowList));
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
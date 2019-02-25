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
public class ProductMapperTest {

    @Autowired
    ProductMapper productMapper;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    @Rollback //物品的增删改查
    public void testAddDeleteUpdateSelect(){
        //增加一个新物品
        int i = productMapper.addNewProduct("test",1,1,"fortest");
        //查询物品
        List<Product> products = productMapper.getProductFromAllProduct("test");
        List<Product> products1 = productMapper.getAllProduct();
        //验证是否增加成功,查询是否成功
        Assert.assertEquals(1,i);
        Assert.assertEquals("fortest",products.get(0).getProduct_type());
        Assert.assertNotEquals(0,products1.size());
        //修改物品
        int r = productMapper.updataProduct("test",2,2,"ttt",products.get(0).getProduct_id());
        products = productMapper.getProductFromAllProduct("test");
        //验证修改
        Assert.assertEquals(1,r);
        Assert.assertEquals("ttt",products.get(0).getProduct_type());
        //删除物品
        int d = productMapper.deleteProductByid(products.get(0).getProduct_id());
        products = productMapper.getProductFromAllProduct("test");
        //验证
        Assert.assertEquals(0,products.size());
    }


    @After
    public void tearDown() throws Exception {
    }
}
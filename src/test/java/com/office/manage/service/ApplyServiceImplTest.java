package com.office.manage.service;

import com.office.manage.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ApplyServiceImplTest {

    @Autowired
    ApplyService applyService;

    @Autowired
    ApplyListMapper applyListMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    BorrowListMapper borrowListMapper;

    @Test
    @Rollback //测试提交申请事务
    public void submitProductApply() {
        //添加一个物品用于测试
        int i = productMapper.addNewProduct("test",2,2,"fortest");
        //查询物品
        List<Product> products = productMapper.getProductFromAllProduct("test");
        Product product = products.get(0);
        //插入一条申请数据
        int r = applyListMapper.addProductApply(888,"admin","admintest",product.getProduct_id(),product.getProduct_name(),1,1,"2019-2-10");
        //执行方法，获取申请列表
        List<ApplyList> applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        ApplyList applyList = applyLists.get(0);
        //提交申请
        try {
            boolean a = applyService.submitProductApply(applyList);
            //再次查询物品
            products = productMapper.getProductFromAllProduct("test");
            product = products.get(0);
            Assert.assertTrue(a);
            Assert.assertEquals(1,product.getProduct_num());
            //不成功
            applyList.setApply_product_id(666);
            Assert.assertFalse(applyService.submitProductApply(applyList));
        }catch (Exception e){
            e.printStackTrace();
        }


    }




    @Test
    @Rollback //测试修改申请事务
    public void updataApplyCheck() {
        //添加一个物品用于测试
        int i = productMapper.addNewProduct("test",2,2,"fortest");
        //查询物品
        List<Product> products = productMapper.getProductFromAllProduct("test");
        Product product = products.get(0);
        //插入一条申请数据
        int r = applyListMapper.addProductApply(888,"admin","admintest",product.getProduct_id(),product.getProduct_name(),1,1,"2019-2-10");
        //执行方法，获取申请列表，修改申请状态为已审核(pass=1)
        List<ApplyList> applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        int result = applyListMapper.updateApplyCheckById(1,1,"admin","2019-2-10",applyLists.get(0).getApply_id());
        //执行方法，获取申请列表
        applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        ApplyList applyList = applyLists.get(0);


        //修改申请
        try {
            boolean a = applyService.updataApplyCheck(applyList);
            //搜索记录
            List<BorrowList> borrowLists = borrowListMapper.getBorrowListById(applyList.getApply_user_id());
            BorrowList borrowList = borrowLists.get(0);
            //验证申请通过插入到借入表的记录
            Assert.assertEquals("admin",borrowList.getBorrowinfo_user_truename());
            Assert.assertEquals("test",borrowList.getBorrowinfo_product_name());
            Assert.assertEquals(1,borrowList.getBorrowinfo_num());
            Assert.assertFalse(applyService.updataApplyCheck(null));
        }catch (Exception e){
            e.printStackTrace();
        }
        //修改申请状态为已审核(pass=2)
        result = applyListMapper.updateApplyCheckById(2,1,"admin","2019-2-10",applyLists.get(0).getApply_id());
        //执行方法，获取申请列表
        applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        applyList = applyLists.get(0);

        //修改申请为驳回
        try {
            boolean a = applyService.updataApplyCheck(applyList);
            //查询物品
            products = productMapper.getProductFromAllProduct("test");
            product = products.get(0);
            Assert.assertEquals("test",product.getProduct_name());
            Assert.assertEquals(3,product.getProduct_num());
            applyList.setApply_product_id(777);
            a = applyService.updataApplyCheck(applyList);
            Assert.assertFalse(a);

            result = applyListMapper.updateApplyCheckById(4,1,"admin","2019-2-10",applyLists.get(0).getApply_id());
            applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
            applyList = applyLists.get(0);
            a = applyService.updataApplyCheck(applyList);
            Assert.assertFalse(a);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
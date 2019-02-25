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

    }

    @Test
    @Rollback //提交申请，插入物品申请记录方法
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

    @Test
    @Rollback //获取未审核的申请列表（待审核pass=0）
    public void testGetApplyCheckList(){
        //执行方法，获取未审核的申请列表
        List<ApplyList> applyLists = applyListMapper.getApplyCheckList();
        //获取查询出来的申请列表的pass字段是否为0
        for(ApplyList applyList : applyLists){
            Assert.assertEquals(0,applyList.getApply_pass());
        }
    }

    @Test
    @Rollback //获取所有申请记录（待审核、通过、驳回）
    public void testGetApplyList(){
        //插入一条申请数据
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        //执行方法，获取所有申请列表
        List<ApplyList> applyLists = applyListMapper.getApplyList();
        List<ApplyList> applyLists1 = applyListMapper.getApplyList();
        //获取的列表不为0
        Assert.assertNotEquals(0,applyLists.size());
        Assert.assertNotEquals(0,applyLists1.size());
        //前面插入的数据也包含在内
        Assert.assertEquals("admintest",applyLists.get(applyLists.size()-1).getApply_user_name());
    }

    @Test
    @Rollback //根据申请人姓名和物品名称模糊搜索申请记录（待审核的记录：pass=0）
    public void testGetApplyCheckByUserNameAndProductName(){
        //插入一条申请数据
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        //尝试模糊查询能否查询出
        Assert.assertEquals(1,r);
        //模糊搜索要以格式 %关键字% 进行搜索
        List<ApplyList> applyLists = applyListMapper.getApplyCheckByUserNameAndProductName("%t%");
        int i = 0;
        for(ApplyList applyList : applyLists){
            if(applyList.getApply_user_name().equals("admintest") && applyList.getApply_user_truename().equals("admin")){
                i++;
            }
            //验证查询的记录是否是未审核状态
            Assert.assertEquals(0,applyList.getApply_pass());
        }
        //查询出刚刚插入的记录
        Assert.assertEquals(1,i);

    }

    @Test
    @Rollback //根据申请人姓名和物品名称模糊搜索申请记录（所有记录）
    public void testGetApplyListByUserNameAndProductName(){
        //插入一条申请数据
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        //模糊搜索要以格式 %关键字% 进行搜索
        List<ApplyList> applyLists = applyListMapper.getApplyListByUserNameAndProductName("%t%");
        int i = 0;
        for(ApplyList applyList : applyLists){
            if(applyList.getApply_user_name().equals("admintest") && applyList.getApply_user_truename().equals("admin")){
                i++;
            }
            //验证查询的记录是否是未审核状态
            Assert.assertEquals(0,applyList.getApply_pass());
        }
        //查询出刚刚插入的记录
        Assert.assertEquals(1,i);
    }

    @Test
    @Rollback //通过申请ID对申请审核情况进行修改(修改审核情况，审核人，审核时间)
    public void testUpdateApplyCheckById(){
        //插入一条申请数据用于修改
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        //获取刚刚插入的数据
        List<ApplyList> applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        System.out.println(applyLists.get(0).getApply_user_name());
        //验证刚刚插入数据的pass值为0（代表未审核）
        Assert.assertEquals(0,applyLists.get(0).getApply_pass());
        //修改刚刚插入的申请记录,修改pass值为1
        int result = applyListMapper.updateApplyCheckById(1,1,"admin","2019-2-10",applyLists.get(0).getApply_id());
        applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        //验证修改是否成功
        Assert.assertEquals(1,result);
        Assert.assertEquals(1,applyLists.get(0).getApply_pass());
    }

    @Test
    @Rollback //通过申请ID删除申请记录
    public void testDeleteApplyById(){
        //插入一条申请数据用于修改
        int r = applyListMapper.addProductApply(1,"admin","admintest",2,"test",2,2,"2019-2-10");
        List<ApplyList> applyLists = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        //验证是否插入成功
        Assert.assertNotEquals(null,applyLists.get(0));
        //删除记录
        int i = applyListMapper.deleteApplyById(applyLists.get(0).getApply_id());
        //验证是否删除成功
        List<ApplyList> applyLists2 = applyListMapper.getApplyListByUserNameAndProductName("%test%");
        Assert.assertEquals(1,i);
        Assert.assertEquals(0,applyLists2.size());
    }



    @After
    public void tearDown() throws Exception {
    }
}
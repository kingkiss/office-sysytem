package com.office.manage.service;

import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.office.manage.domain.ApplyList;
import com.office.manage.domain.ApplyListMapper;
import com.office.manage.domain.BorrowListMapper;
import com.office.manage.domain.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyListMapper applyListMapper;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    BorrowListMapper borrowListMapper;

    //提交申请事务
    @Transactional
    public boolean submitProductApply(ApplyList applyList) throws Exception {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        //添加申请到申请表
        int result1 = applyListMapper.addProductApply(applyList.getApply_user_id(),applyList.getApply_user_truename(),applyList.getApply_user_name(),applyList.getApply_product_id(),applyList.getApply_product_name(),applyList.getApply_num(),applyList.getApply_product_price(),datetime);
        //添加完申请表在物品表减去对应物品的库存
        int result2 = productMapper.updataProductNum(applyList.getApply_num(),applyList.getApply_product_id());
        if(result1 == 1 && result2 ==1){
            return true;
        }else {
            return false;
        }
    }

    //修改申请审核事务
    @Transactional
    public boolean updataApplyCheck(ApplyList applyList)  {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        //更新申请表
        int applyResult = applyListMapper.updateApplyCheckById(applyList.getApply_pass(),applyList.getApply_passman_id(),applyList.getApply_passman_name(),datetime,applyList.getApply_id());
        //apply_pass=1则审核通过，审核通过插入借入记录表
        if( applyList.getApply_pass() == 1 ){
            int borrowResult = borrowListMapper.addBorrowList(applyList.getApply_user_id(),applyList.getApply_user_truename(),applyList.getApply_product_id(),applyList.getApply_product_name(),applyList.getApply_num(),applyList.getApply_product_price(),datetime);
            if( borrowResult == 1 ){
                return true;
            }else {
                return false;
            }
        }else if( applyList.getApply_pass() ==2 && applyResult == 1  ){
            //pass=2为申请审核状态为驳回，物品存回数据库
            int productResult = productMapper.updataProductNumA(applyList.getApply_num(),applyList.getApply_product_id());
            if (productResult==1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
}

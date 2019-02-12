package com.office.manage.service;

import java.lang.Exception;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.office.manage.domain.ApplyList;
import com.office.manage.domain.ApplyListMapper;
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

    @Transactional
    public boolean submitProductApply(ApplyList applyList) throws Exception {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        //添加申请到申请表
        int result1 = applyListMapper.addProductApply(applyList.getApply_user_id(),applyList.getApply_user_truename(),applyList.getApply_product_id(),applyList.getApply_product_name(),applyList.getApply_num(),datetime);
        //添加完申请表在物品表减去对应物品的库存
        int result2 = productMapper.updataProductNum(applyList.getApply_num(),applyList.getApply_product_id());
        if(result1 == 1 && result2 ==1){
            return true;
        }else {
            throw new Exception("数据库异常");
        }
    }
}

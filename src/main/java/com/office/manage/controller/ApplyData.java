package com.office.manage.controller;


import com.office.manage.domain.ApplyList;
import com.office.manage.domain.ApplyListMapper;
import com.office.manage.domain.Message;
import com.office.manage.domain.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplyData {
    @Autowired
    ApplyListMapper applyListMapper;

    @Autowired
    ProductMapper productMapper;

    //提交物品申请
    @RequestMapping(value = "/addApply",method = RequestMethod.POST)
    public Message addApply(@RequestBody ApplyList applyList){
        Message msg = new Message();
        int product_num = productMapper.getProductNumById(applyList.getApply_product_id());
        if(applyList.getApply_num() <= product_num){
            int result1 = applyListMapper.addProductApply(applyList.getApply_user_id(),applyList.getApply_product_id(),applyList.getApply_num(),applyList.getApply_time());
            if(result1 == 1){
                int result2 = productMapper.updataProductNum(applyList.getApply_num(),applyList.getApply_product_id());
                msg.setResult(true);
                msg.setInfo("申请已经提交，等待审核！");
                return msg;
            }else{
                msg.setResult(false);
                msg.setInfo("未知错误请重试！");
                return msg;
            }
        }else{
            msg.setResult(false);
            msg.setInfo("申请物品数量超过库存！");
            return  msg;
        }

    }


}

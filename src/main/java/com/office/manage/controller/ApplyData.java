package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.*;
import com.office.manage.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplyData {
    @Autowired
    ApplyListMapper applyListMapper;

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ApplyService applyService;

    //提交物品申请
    @RequestMapping(value = "/addApply",method = RequestMethod.POST)
    public Message addApply(@RequestBody ApplyList applyList){
        Message msg = new Message();
        int product_num = productMapper.getProductNumById(applyList.getApply_product_id());
        if(applyList.getApply_num() <= product_num){
            try {
                boolean r = applyService.submitProductApply(applyList);
                if(r){
                    msg.setResult(true);
                    msg.setInfo("申请已经提交，等待审核！");
                    return msg;
                }else{
                    msg.setResult(false);
                    msg.setInfo("未知错误请重试！");
                    return msg;
                }
            }catch (Exception e){
                msg.setInfo(e.getMessage());
                msg.setResult(false);
                return msg;
            }
        }else{
            msg.setResult(false);
            msg.setInfo("申请物品数量超过库存！");
            return  msg;
        }

    }

    //获取所有申请审核记录
    @RequestMapping(value = "/allApplyCheck",method = RequestMethod.GET)
    public Map<String,Object> getAllProductList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                @RequestParam(value = "orderBy" ,defaultValue = "apply_id asc") String orderBy) {
        PageHelper.startPage(start, size, orderBy);
        List<ApplyList> applyLists = applyListMapper.getApplyList();
        PageInfo<ApplyList> page = new PageInfo<>(applyLists);
        Map<String, Object> m = new HashMap<>();
        m.put("AllApplyChecks", applyLists);
        m.put("page", page);
        return m;
    }

}

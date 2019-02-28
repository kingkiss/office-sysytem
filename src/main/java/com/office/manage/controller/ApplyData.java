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
    public Map<String,Object> getAllApplyCheckList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                @RequestParam(value = "orderBy" ,defaultValue = "apply_id asc") String orderBy) {
        PageHelper.startPage(start, size, orderBy);
        List<ApplyList> applyLists = applyListMapper.getApplyCheckList();
        PageInfo<ApplyList> page = new PageInfo<>(applyLists);
        Map<String, Object> m = new HashMap<>();
        m.put("AllApplyChecks", applyLists);
        m.put("page", page);
        if(applyLists.size() != 0){
            m.put("testcase",true);
        }else {
            m.put("testcase",false);
        }
        return m;
    }


    //获取所有申请记录
    @RequestMapping(value = "/allApplyList",method = RequestMethod.GET)
    public Map<String,Object> getAllApplyList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                @RequestParam(value = "orderBy" ,defaultValue = "apply_id asc") String orderBy) {
        PageHelper.startPage(start, size, orderBy);
        List<ApplyList> applyLists = applyListMapper.getApplyList();
        PageInfo<ApplyList> page = new PageInfo<>(applyLists);
        Map<String, Object> m = new HashMap<>();
        m.put("AllApplyLists", applyLists);
        m.put("page", page);
        if(applyLists.size() != 0){
            m.put("testcase",true);
        }else {
            m.put("testcase",false);
        }
        return m;
    }


    //根据申请人姓名和物品名称模糊搜索申请记录（待审核的记录：pass=0）
    @RequestMapping(value = "/SearchApplyCheck",method = RequestMethod.GET)
    public Map<String,Object> getSearchApplyCheck(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                               @RequestParam(value = "applyCheck",defaultValue = "") String apply){
        String search = "%"+apply+"%";
        PageHelper.startPage(start,size);
        List<ApplyList> applyChecks = applyListMapper.getApplyCheckByUserNameAndProductName(search);
        PageInfo<ApplyList> Searchpage = new PageInfo<>(applyChecks);
        Map<String,Object> su = new HashMap<>();
        su.put("SearchApplyChecks",applyChecks);
        su.put("page",Searchpage);
        if(applyChecks.size() != 0){
            su.put("testcase",true);
        }else {
            su.put("testcase",false);
        }
        return su;
    }


    //根据申请人姓名和物品名称模糊搜索申请记录（所有记录）
    @RequestMapping(value = "/SearchApplyList",method = RequestMethod.GET)
    public Map<String,Object> getSearchApplyList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                               @RequestParam(value = "applyCheck",defaultValue = "") String apply){
        String search = "%"+apply+"%";
        PageHelper.startPage(start,size);
        List<ApplyList> applyChecks = applyListMapper.getApplyListByUserNameAndProductName(search);
        PageInfo<ApplyList> Searchpage = new PageInfo<>(applyChecks);
        Map<String,Object> su = new HashMap<>();
        su.put("SearchApplyLists",applyChecks);
        su.put("page",Searchpage);
        if(applyChecks.size() != 0){
            su.put("testcase",true);
        }else {
            su.put("testcase",false);
        }
        return su;
    }

    //申请审核提交（1=通过，2=驳回）
    @RequestMapping(value = "/sumbitApplyCheck",method = RequestMethod.POST)
    public Message updataApplyCheckStatus(@RequestBody ApplyList applyList){
        Message msg = new Message();
        try {
            boolean r = applyService.updataApplyCheck(applyList);
            if(r){
                msg.setResult(true);
                msg.setInfo("该申请已审核！");
                return msg;
            }else{
                msg.setResult(false);
                msg.setInfo("网络错误请重试！");
                return msg;
            }
        }catch (Exception e){
            msg.setInfo(e.getMessage());
            msg.setResult(false);
            return msg;
        }


    }

    //删除申请记录
    @RequestMapping(value = "/deleteApply/{apply_id}",method = RequestMethod.DELETE)
    public Message deleteApply(@PathVariable int apply_id){
        Message msg = new Message();
        int result = applyListMapper.deleteApplyById(apply_id);
        //System.out.println(result);
        if( result>0 ){
            msg.setResult(true);
            msg.setInfo("已删除"+result+"条记录");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误请重试");
            return msg;
        }
    }


}

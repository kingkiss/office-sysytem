package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.*;
import com.office.manage.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BorrowData {

    @Autowired
    BorrowListMapper borrowListMapper;

    @Autowired
    BorrowService borrowService;

    //获取对应用户的借入记录（管理员可查看所有借入记录）
    @RequestMapping(value = "/allBorrowList",method = RequestMethod.GET)
    public Map<String,Object> getAllBorrowList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                               HttpServletRequest request) throws Exception {
        PageHelper.startPage(start, size);
        List<BorrowList> borrowLists;
        HttpSession session = request.getSession();
        Integer  uid = (Integer) session.getAttribute("user_id");
        Integer  uah = (Integer) session.getAttribute("user_authority");
        int user_id = 1;
        int authority =  1;
        if( uid != null && uah != null ){
            user_id = Integer.valueOf(uid);
            authority = Integer.valueOf(uah);
        }
//        System.out.println("id:"+i);
//        System.out.println("auth:"+a);
        //判断是不是管理员（7）
        if( authority >= 3){
            borrowLists = borrowListMapper.getAllBorrowList();
        }else {
            borrowLists = borrowListMapper.getBorrowListById(user_id);
        }
        PageInfo<BorrowList> page = new PageInfo<>(borrowLists);
        Map<String, Object> m = new HashMap<>();
        m.put("AllBorrowLists", borrowLists);
        m.put("page", page);
        if(borrowLists.size() != 0){
            m.put("testcase",true);
        }else {
            m.put("testcase",false);
        }
        return m;
    }

    //根据借入物品名模糊查询借入记录
    @RequestMapping(value = "/SearchBorrowList",method = RequestMethod.GET)
    public Map<String,Object> getSearchBorrowList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                                  @RequestParam(value = "borrowList",defaultValue = "") String borrow){
        String search = "%"+borrow+"%";
        PageHelper.startPage(start,size);
        List<BorrowList> borrowLists = borrowListMapper.searchBorrowById(search);
        PageInfo<BorrowList> Searchpage = new PageInfo<>(borrowLists);
        Map<String,Object> su = new HashMap<>();
        su.put("SearchBorrowLists",borrowLists);
        su.put("page",Searchpage);
        if(borrowLists.size() != 0){
            su.put("testcase",true);
        }else {
            su.put("testcase",false);
        }
        return su;
    }

    //删除借入归还记录
    @RequestMapping(value = "/deleteBorrow/{borrowinfo_id}",method = RequestMethod.DELETE)
    public Message deleteBorrowList(@PathVariable int borrowinfo_id){
        Message msg = new Message();
        int result = borrowListMapper.deleteBorrowById(borrowinfo_id);
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

    //借出记录归还挂失处理
    @RequestMapping(value = "/updateBorrow",method = RequestMethod.POST)
    public Message updateBorrowList(@RequestBody BorrowList borrowList) {
        Message msg = new Message();
        int flag = borrowList.getBorrowinfo_return();
        //归还
        if( flag==1 ){
            try{
                boolean r = borrowService.returnBorrow(borrowList);
                if(r){
                    msg.setResult(true);
                    msg.setInfo("该申请已审核！");
                    return msg;
                }else {
                    msg.setResult(false);
                    msg.setInfo("网络错误请重试！");
                    return msg;
                }
            }catch (Exception e){
                msg.setInfo(e.getMessage());
                msg.setResult(false);
                return msg;
            }

        }else {
            //挂失
            try{
                boolean r = borrowService.missBorrow(borrowList);
                if(r){
                    msg.setResult(true);
                    msg.setInfo("该申请已审核！");
                    return msg;
                }else {
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
    }
}

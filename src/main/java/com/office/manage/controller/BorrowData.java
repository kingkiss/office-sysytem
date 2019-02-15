package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.ApplyList;
import com.office.manage.domain.BorrowList;
import com.office.manage.domain.BorrowListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BorrowData {

    @Autowired
    BorrowListMapper borrowListMapper;


    //获取对应用户的借入记录（管理员可查看所有借入记录）
    @RequestMapping(value = "/allBorrowList",method = RequestMethod.GET)
    public Map<String,Object> getAllBorrowList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                               HttpServletRequest request) throws Exception {
        PageHelper.startPage(start, size);
        List<BorrowList> borrowLists;
        HttpSession session = request.getSession();
        int user_id = (int)session.getAttribute("user_id");
        int authority = (int)session.getAttribute("user_authority");
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
        return su;
    }


}

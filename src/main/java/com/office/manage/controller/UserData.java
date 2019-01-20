package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserData {
    @Autowired
    UserMapper userMapper;

    //获取用户列表
    //pagehelper分页
    @RequestMapping(value = "/u",method = RequestMethod.GET)
    public Map<String,Object> getUserList(@RequestParam(value = "start",defaultValue = "0") int start , @RequestParam(value = "size" ,defaultValue = "15") int size){
        PageHelper.startPage(start,size);
        List<User> users = userMapper.getAllUser();
        PageInfo<User> page = new PageInfo<>(users);
        Map<String,Object> m = new HashMap<>();
        m.put("users",users);
        m.put("page",page);
        return m;
    }

    @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
    public List<User> getAllUser(){
        List<User> u = userMapper.getAllUser();
        return u;
    }

}

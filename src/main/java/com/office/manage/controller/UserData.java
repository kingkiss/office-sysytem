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

import java.util.List;

@RestController
public class UserData {
    @Autowired
    UserMapper userMapper;

    //获取用户列表
    //pagehelper分页
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> getUserList(Model m, @RequestParam(value = "start",defaultValue = "0") int start ,@RequestParam(value = "size" ,defaultValue = "15") int size){
        PageHelper.startPage(start,size,"id desc");
        List<User> users = userMapper.getAllUser();
        PageInfo<User> page = new PageInfo<>(users);
        return users;
    }
}

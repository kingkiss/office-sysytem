package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.Message;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserData {
    @Autowired
    UserMapper userMapper;

    //获取用户列表
    //pagehelper分页
    @RequestMapping(value = "/allUsers",method = RequestMethod.GET)
    public Map<String,Object> getUserList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                          @RequestParam(value = "orderBy" ,defaultValue = "user_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<User> users = userMapper.getAllUser();
        PageInfo<User> page = new PageInfo<>(users);
        Map<String,Object> m = new HashMap<>();
        m.put("users",users);
        m.put("page",page);
        if(users.size() != 0){
            m.put("testcase",true);
        }else {
            m.put("testcase",false);
        }
        return m;
    }

    //搜索用户
    @RequestMapping(value = "/Search",method = RequestMethod.GET)
    public Map<String,Object> getSearchUserList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
    @RequestParam String s){
        String search = s+"%";
        PageHelper.startPage(start,size);
        List<User> Searchusers = userMapper.getUserByNameAndEmail(search);
        PageInfo<User> Searchpage = new PageInfo<>(Searchusers);
        Map<String,Object> su = new HashMap<>();
        su.put("users",Searchusers);
        su.put("page",Searchpage);
        if(Searchusers.size() != 0){
            su.put("testcase",true);
        }else {
            su.put("testcase",false);
        }
        return su;
    }

    //添加新用户
    @RequestMapping(value = "/adduser",method = RequestMethod.POST)
    public Message addUser(@RequestBody User user){
        Message msg = new Message();
        System.out.println(user.getUser_name());
        User user1 = userMapper.findUser(user.getUser_name()+"@office.com");
        if(user1 == null){
            int result = userMapper.addUser(user.getUser_name()+"@office.com",user.getUser_password(),user.getUser_truename(),user.getUser_phone(),user.getUser_department(),user.getUser_authority());
            if( result == 1 ){
                msg.setResult(true);
                msg.setInfo("添加新用户成功");
                return msg;
            }else {
                msg.setResult(false);
                msg.setInfo("添加用户失败");
                return msg;
            }
        }else {
            msg.setResult(false);
            msg.setInfo("用户已存在");
            return msg;
        }
    }

    //删除用户
    @RequestMapping(value = "/delete/{user_name}" ,method = RequestMethod.DELETE)
    public Message deleteUser(@PathVariable String user_name){
        Message msg = new Message();
        //System.out.println(user_name);
        int result = userMapper.deleteUser(user_name);
        //System.out.println(result);
        if( result>0 ){
            msg.setResult(true);
            msg.setInfo("已删除"+result+"个用户");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误请重试");
            return msg;
        }

    }

    //修改用户信息
    @RequestMapping(value = "/update/{user_name}",method = RequestMethod.PUT)
    public Message updateUser(@PathVariable String user_name,@RequestBody User user){
        Message msg = new Message();
//        System.out.println(user_name);
//        System.out.println(user.getUser_truename());
        int result = userMapper.updateUserByName(user.getUser_truename(),user.getUser_phone(),user.getUser_department(),user.getUser_authority(),user_name);
//       System.out.println(result);
        if(result>0){
            msg.setResult(true);
            msg.setInfo("用户信息已修改！");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误，");
            return msg;
        }
    }


}

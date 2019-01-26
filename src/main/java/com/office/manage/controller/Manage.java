package com.office.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Manage {
	//登录入口
    @RequestMapping("/login")
    public String login(){
    	return "login";
    }

    //退出登录
    @RequestMapping("/loginout")
    public String loginout(HttpServletRequest request){
    	HttpSession session = request.getSession();
    	session.invalidate();
    	return "login";
    }

    //页面跳转到管理主页面(物品分类)
    @RequestMapping("/manage")
    public String manage(){
        return "manage";
    }

    //用户管理模块的页面
    @RequestMapping("/manage_userInfo")
    public String manage_userInfo(){ return "manage_userInfo"; }

}

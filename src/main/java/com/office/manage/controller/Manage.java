package com.office.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String manage(Model m){
        boolean isUserInfo = false;
        boolean isOfficeList = true;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        return "manage";
    }

    //用户管理模块的页面
    @RequestMapping("/manage_userInfo")
    public String manage_userInfo(Model m,HttpServletRequest request){
        boolean isUserInfo = true;
        boolean isOfficeList = false;
        HttpSession session = request.getSession();
        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);

        return "manage";
    }

    @RequestMapping("/manage_officelist")
    public String manage_officelist(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = true;
        HttpSession session = request.getSession();
        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);

        return "manage";
    }

}

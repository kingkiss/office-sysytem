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

    //页面跳转到管理主页面(物品申请)
    @RequestMapping("/manage")
    public String manage(Model m){
        boolean isUserInfo = false;
        boolean isOfficeList = true;
        boolean isOfficeInfo = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        return "manage";
    }

    //用户管理模块的页面
    @RequestMapping("/manage_userInfo")
    public String manage_userInfo(Model m,HttpServletRequest request){
        boolean isUserInfo = true;
        boolean isOfficeList = false;
        boolean isOfficeInfo = false;

        HttpSession session = request.getSession();
        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);

        return "manage";
    }

    //页面跳转到物品申请管理页面(物品申请)-_-!
    @RequestMapping("/manage_officelist")
    public String manage_officelist(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = true;
        boolean isOfficeInfo = false;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);
        return "manage";
    }

    //页面跳转到物品管理页面(物品管理)
    @RequestMapping("/manage_officeInfo")
    public String manage_officeInfo(Model m,HttpServletRequest request){
        boolean isUserInfo = false;
        boolean isOfficeList = false;
        boolean isOfficeInfo = true;

        m.addAttribute("isUserInfo",isUserInfo);
        m.addAttribute("isOfficeList",isOfficeList);
        m.addAttribute("isOfficeInfo",isOfficeInfo);

        return "manage";
    }

}

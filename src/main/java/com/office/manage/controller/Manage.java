package com.office.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Manage {

	//页面跳转到管理主页面
    @RequestMapping("/manage")
    public String manage(){
    	return "manage";
    }
}

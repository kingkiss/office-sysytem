package com.office.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMain {
    @RequestMapping("/manage")
    public String test(){
    	return "manage";
    }
}

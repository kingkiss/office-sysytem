package com.office.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMain {
    @RequestMapping("/login")
    public String test(){
    	return "login";
    }
}

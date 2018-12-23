package com.office.manage.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	
	@RequestMapping("/user")
	public User getUser(){
		List<User> users = userMapper.findAll();
		return users.get(0);
	}
}

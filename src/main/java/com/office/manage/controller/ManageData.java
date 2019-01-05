package com.office.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.office.manage.domain.User;

@RestController
public class ManageData {

	@RequestMapping("/userInfo")
	public User userInfo(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = new User();
		//把session中的用户信息传入实体
		user.setUser_id((int)session.getAttribute("user_id"));
		user.setUser_name((String)session.getAttribute("user_name"));
		user.setUser_password((String)session.getAttribute("user_password"));
		user.setUser_truename((String)session.getAttribute("user_truename"));
		user.setUser_department((String)session.getAttribute("user_department"));
		user.setUser_authority((int)session.getAttribute("user_authority"));
		
		return user;
	}
}

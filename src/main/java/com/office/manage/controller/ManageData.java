package com.office.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.office.manage.domain.Message;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;

@RestController
public class ManageData {
	
	@Autowired UserMapper userMapper;

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
		user.setUser_phone((String)session.getAttribute("user_phone"));
		return user;
	}
	
	//用于用户自主修改姓名
	@RequestMapping(value="/changeUserName",method=RequestMethod.POST)
	public Message ChangeUserName(@RequestBody Map<String, String> userJsonData ,HttpServletRequest request){
		Message msg = new Message();
		HttpSession session = request.getSession();
		String newUserTruename = userJsonData.get("userInfo_change_name").toString();
		int result = userMapper.updateUserTruenameByName(newUserTruename, (String)session.getAttribute("user_name"));
		System.out.println("修改用户名返回结果："+result);
		if( result == 1 ){
			msg.setResult(true);
			return msg;
		}else{
			msg.setResult(false);
			return msg;
		}
		
	}
	
	//用于用户自主修改电话
	@RequestMapping(value="/changeUserPhone",method=RequestMethod.POST)
	public Message ChangeUserPhone(@RequestBody User user ,HttpServletRequest request){
		Message msg = new Message();
		
		
		return msg;
	}
	
}

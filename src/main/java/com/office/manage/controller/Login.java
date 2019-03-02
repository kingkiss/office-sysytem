package com.office.manage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.office.manage.domain.Message;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;

@RestController
public class Login {

	@Autowired UserMapper userMapper;
	
	@RequestMapping(value="/userLogin",method=RequestMethod.POST)
	public Message getUser(@RequestBody User user ,HttpServletRequest request){
//		System.out.println("ln:"+user.getUser_name());
//		System.out.println("lp:"+user.getUser_password());
//		万一用户名或者密码错误查询不出user会空指针异常,需要进一步判断
		User user1 = userMapper.findUser(user.getUser_name());
		Message msg = new Message();
		if(user1 == null){
			msg.setInfo("登录失败");
			msg.setResult(true);
			return msg;
		}else if(user.getUser_password().equals(user1.getUser_password())){
			//设置session
			HttpSession session = request.getSession(true);
			session.setAttribute("user_id", user1.getUser_id());
			session.setAttribute("user_name", user1.getUser_name());
			session.setAttribute("user_password", user1.getUser_password());
			session.setAttribute("user_truename", user1.getUser_truename());
			session.setAttribute("user_department", user1.getUser_department());
			session.setAttribute("user_authority", user1.getUser_authority());
			session.setAttribute("user_phone", user1.getUser_phone());
			msg.setInfo("success");
			return msg;
		}else{
			msg.setInfo("登录失败");
			msg.setResult(true);
			return msg;
		}

	}

	
}

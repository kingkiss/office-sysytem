package com.office.manage.controller;



import static org.hamcrest.CoreMatchers.nullValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.office.manage.domain.Message;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;

@RestController
public class UserController {
	@Autowired UserMapper userMapper;
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public Message getUser(@RequestBody User user){
		System.out.println("ln:"+user.getUser_name());
		System.out.println("lp:"+user.getUser_password());
//		万一用户名或者密码错误查询不出user会空指针异常,需要进一步判断
		User user1 = userMapper.findUser(user.getUser_name());
		Message msg = new Message();
		if(user1 == null){
			msg.setResult(true);
			return msg;
		}
		if(user.getUser_password().equals(user1.getUser_password())){
			msg.setResult(false);;
			return msg;
		}else{
			msg.setResult(true);;
			return msg;
		}

	}
	//TODO页面跳转
	
}

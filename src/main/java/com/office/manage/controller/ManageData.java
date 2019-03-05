package com.office.manage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.office.manage.domain.Message;
import com.office.manage.domain.User;
import com.office.manage.domain.UserMapper;

@RestController
public class ManageData {

	@Autowired
	UserMapper userMapper;

	@RequestMapping("/userInfo")
	public User userInfo(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		User user = new User();
		//把session中的用户信息传入实体
		try{
			user.setUser_id((int)session.getAttribute("user_id"));
			user.setUser_name((String)session.getAttribute("user_name"));
			user.setUser_password((String)session.getAttribute("user_password"));
			user.setUser_truename((String)session.getAttribute("user_truename"));
			user.setUser_department((String)session.getAttribute("user_department"));
			user.setUser_authority((int)session.getAttribute("user_authority"));
			user.setUser_phone((String)session.getAttribute("user_phone"));
			return user;
		}catch (NullPointerException n){
			user.setUser_authority(4396);
			return user;
		}

	}
	
	//用于用户自主修改姓名
	@RequestMapping(value="/changeUserName",method=RequestMethod.POST)
	public Message ChangeUserName(@RequestBody User user, HttpServletRequest request){
		Message msg = new Message();
		HttpSession session = request.getSession();
		String newName = user.getUser_truename();
		String u_name = (String)session.getAttribute("user_name");
		int result = userMapper.updateUserTruenameByName(newName,u_name );
//		System.out.println("修改用户名返回结果："+result);
		if( result == 1 ){
			msg.setResult(true);
			msg.setInfo("success");
			session.setAttribute("user_truename", user.getUser_truename());;
			return msg;
		}else{
			msg.setResult(false);
			msg.setInfo("fail");
			return msg;
		}
		
	}
	
	//用于用户自主修改电话
	@RequestMapping(value="/changeUserPhone",method=RequestMethod.POST)
	public Message ChangeUserPhone(@RequestBody User user,HttpServletRequest request){
		Message msg = new Message();
		HttpSession session = request.getSession();
		System.out.println(user.getUser_phone());
		int result = userMapper.updateUserPhoneByName(user.getUser_phone(), (String)session.getAttribute("user_name"));
//		System.out.println("修改用户电话返回结果："+result);
		if( result == 1 ){
			msg.setResult(true);
			msg.setInfo("success");
			session.setAttribute("user_phone", user.getUser_phone());
			return msg;
		}else{
			msg.setResult(false);
			msg.setInfo("fail");
			return msg;
		}
	}

	//用于用户修改密码
	@RequestMapping(value="/changeUserPwd",method=RequestMethod.POST)
	public Message ChangeUserPwd(@RequestParam Map<String, Object> UserPwd,HttpServletRequest request){
		Message msg = new Message();
		HttpSession session = request.getSession();
		String user_password = userMapper.finUserPwdByName(UserPwd.get("user_name").toString());
		String userPwd = UserPwd.get("userOldPwd").toString();
		System.out.println("用户名查询的旧密码："+user_password);
		System.out.println("前端数据："+userPwd);
		//判断旧密码是否正确
		try{
			if(user_password.equals(userPwd)){
				int result = userMapper.updateUserPwdByName(UserPwd.get("userNewPwd").toString(),(String)session.getAttribute("user_name"));
				System.out.println("修改用户密码结果："+ result);
				msg.setResult(true);
				msg.setInfo("用户密码修改成功,请重新登录！");
				return msg;
			}else{
				msg.setResult(false);
				msg.setInfo("旧密码错误，请重试");
				return msg;
			}
		}catch (NullPointerException e){
			msg.setResult(false);
			msg.setInfo("旧密码错误，请重试");
			return msg;
		}

	}
	
}

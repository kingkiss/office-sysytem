package com.office.manage.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
	
	
	@Select("select * from user_info")
	public List<User> getAllUser();

	//根据用户名查询用户信息
	@Select("select * from user_info where user_name = #{u_name}")
	public User findUser(String u_name);

	//根据用户名查询用户密码（用于验证密码是否正确）
	@Select("select user_password from user_info where user_name=#{u_name}")
	public String finUserPwdByName(String u_name);

	//根据用户邮箱修改用户姓名
	@Update("update user_info set user_truename=#{u_tn} where user_name=#{u_name}")
	public int updateUserTruenameByName(String u_tn, String u_name);

	//根据用户名修改用户电话
	@Update("update user_info set user_phone=#{u_phone} where user_name=#{u_name}")
	public int updateUserPhoneByName(String u_phone, String u_name);

	//根据用户名修改用户密码
	@Update("update user_info set user_password=#{u_pwd} where user_name=#{u_name}")
	public int updateUserPwdByName(String u_pwd, String u_name);

}

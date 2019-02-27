package com.office.manage.domain;

import java.util.List;

import org.apache.ibatis.annotations.*;
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

	//根据用户ID查询用户名
	@Select("SELECT user_truename FROM user_info WHERE user_id=#{u_id}")
	public String findUserNameById(int u_id);

	//根据用户邮箱和用户名模糊查询用户
	@Select("SELECT * FROM user_info where user_truename LIKE #{search} OR user_name LIKE #{search} OR user_authority=#{search}")
	public List<User> getUserByNameAndEmail(String search);

	//根据用户邮箱修改用户姓名
	@Update("update user_info set user_truename=#{u_tn} where user_name=#{u_name}")
	public int updateUserTruenameByName(String u_tn, String u_name);

	//根据用户名修改用户电话
	@Update("update user_info set user_phone=#{u_phone} where user_name=#{u_name}")
	public int updateUserPhoneByName(String u_phone, String u_name);

	//根据用户名修改用户密码
	@Update("update user_info set user_password=#{u_pwd} where user_name=#{u_name}")
	public int updateUserPwdByName(String u_pwd, String u_name);

	//根据用户邮箱修改用户信息
	@Update("UPDATE user_info SET user_truename=#{u_truename},user_phone=#{u_phone},user_department=#{u_department},user_authority=#{u_authority} WHERE user_name=#{u_name}")
	public int updateUserByName(String u_truename,String u_phone,String u_department,int u_authority,String u_name);

	//添加用户
	@Insert("INSERT INTO user_info (user_name,user_password,user_truename,user_phone,user_department,user_authority) " +
			"VALUES (#{u_name},#{u_password},#{u_truename},#{u_phone},#{u_department},#{u_authority})")
	public int addUser(String u_name,String u_password,String u_truename,String u_phone,String u_department,int u_authority);

	//根据用户邮箱删除用户
	@Delete("DELETE FROM user_info WHERE user_name=#{u_name}")
	public int deleteUser(String u_name);


}

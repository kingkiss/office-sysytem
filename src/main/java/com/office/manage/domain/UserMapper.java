package com.office.manage.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	
	
	@Select("select * from user_info")
	public User getAllUser();
	
	@Select("select * from user_info where user_name = #{u_name}")
	public User findUser(String u_name);
}

package com.office.manage.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
	
	@Select("select * from user_info")
	List<User> findAll();
}

package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApplyListMapper {

	@Select("select * from apply_info")
	public ApplyList getApplyList();
}

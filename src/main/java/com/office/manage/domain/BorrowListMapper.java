package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BorrowListMapper {

	@Select("select * from borrow_info")
	public BorrowList getAllBorrowList();
}

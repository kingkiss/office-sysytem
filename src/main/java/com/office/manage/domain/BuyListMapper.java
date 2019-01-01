package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BuyListMapper {

	@Select("select * from buy_info")
	public BuyList getBuyList();
}

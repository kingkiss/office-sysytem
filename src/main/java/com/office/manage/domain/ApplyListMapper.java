package com.office.manage.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Mapper
public interface ApplyListMapper {

	@Select("select * from apply_info")
	public ApplyList getApplyList();

	//提交申请，插入物品申请记录
	@Insert("INSERT INTO apply_info(apply_user_id,apply_product_id,apply_num,apply_time) VALUES (#{user_id},#{product_id},#{num},#{time})")
	public int addProductApply(int user_id, int product_id, int num, String time);

}

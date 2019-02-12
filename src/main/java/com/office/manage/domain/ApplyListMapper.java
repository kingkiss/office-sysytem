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
	@Insert("INSERT INTO apply_info(apply_user_id,apply_product_id,apply_num,apply_time,apply_pass) VALUES (#{user_id},#{product_id},#{num},#{time},0)")
	public int addProductApply(int user_id, int product_id, int num, String time);

	//查询申请状态（apply_pass:0、1、2;待审核、通过、驳回）
	@Select("SELECT * FROM apply_info WHERE apply_pass=0")
	public int getApplyStatusById(int apply_id);

}

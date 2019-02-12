package com.office.manage.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface ApplyListMapper {

	@Select("select * from apply_info")
	public List<ApplyList> getApplyList();

	//提交申请，插入物品申请记录
	@Insert("INSERT INTO apply_info(apply_user_id,apply_user_truename,apply_product_id,apply_product_name,apply_num,apply_time,apply_pass) " +
			"VALUES (#{user_id},#{user_truename},#{product_id},#{product_name},#{num},#{time},0)")
	public int addProductApply(int user_id, String user_truename, int product_id, String product_name, int num, String time);

	//查询申请状态（apply_pass:0、1、2;待审核、通过、驳回）
	@Select("SELECT * FROM apply_info WHERE apply_pass=0")
	public int getApplyStatusById(int apply_id);

	//查询申请人ID(待审核状态的申请记录)
	@Select("SELECT apply_user_id FROM apply_info WHERE apply_pass=0")
	public int getApplyUserIdByApplyId();

	//查询申请物品ID(待审核状态的申请记录)
	@Select("SELECT apply_product_id FROM apply_info WHERE apply_pass=0")
	public int getApplyProductIdByApplyId();



}

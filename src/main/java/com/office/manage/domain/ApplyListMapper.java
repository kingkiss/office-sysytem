package com.office.manage.domain;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ApplyListMapper {

	/*
	 * 统计未审核所有物品数量
	 * */
	@Select("SELECT SUM(apply_num) FROM apply_info WHERE apply_pass=0")
	public int getAllProductNumData();

	/*
	 * 统计待审核物品的资金
	 * */
	@Select("SELECT SUM(apply_num*apply_product_price) FROM apply_info WHERE apply_pass=0")
	public int getApplyProductMoneyData();

	/*搜索7天内时间记录*/
	@Select("SELECT " +
			"  DISTINCT DATE_FORMAT(apply_time,'%m-%d') as time " +
			"FROM " +
			" apply_info " +
			"WHERE " +
			"DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(apply_time) " +
			"ORDER BY " +
			"  time ASC")
	public List<String> getApplyTime();

	/*
	 * 统计某天内某类型物品的申请数
	 * */
	@Select("SELECT\n" +
			"t.num\n" +
			"FROM\n" +
			"(\n" +
			"SELECT\n" +
			"\tSUM(a.apply_num) AS num,\n" +
			"\ta.time\n" +
			"FROM\n" +
			"\t(\n" +
			"\t\tSELECT\n" +
			"\t\t\tp.product_type,\n" +
			"\t\t\ta.apply_num,\n" +
			"\t\t\tDATE_FORMAT(a.apply_time, '%m-%d') AS time\n" +
			"\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\t\tSELECT\n" +
			"\t\t\t\t\tapply_product_name,\n" +
			"\t\t\t\t\tapply_num,\n" +
			"\t\t\t\t\tapply_time\n" +
			"\t\t\t\tFROM\n" +
			"\t\t\t\t\tapply_info\n" +
			"\t\t\t\tWHERE\n" +
			"\t\t\t\t\tDATE_SUB(CURDATE(), INTERVAL 7 DAY) <= DATE(apply_time)\n" +
			"\t\t\t) AS a\n" +
			"\t\tLEFT JOIN product_info AS p ON a.apply_product_name = p.product_name\n" +
			"\t\tGROUP BY\n" +
			"\t\t\ta.apply_time\n" +
			"\t) AS a\n" +
			"LEFT JOIN type_info AS t ON a.product_type = t.product_type\n" +
			"WHERE\n" +
			"\tt.type_category = #{type}\n" +
			"AND a.time = #{time}\n" +
			") AS t\n")
	public Integer getApplyTypeNumByTypeAndTime(String type,String time);



	//获取未审核的申请列表（待审核pass=0）
	@Select("select * from apply_info where apply_pass=0")
	public List<ApplyList> getApplyCheckList();

	//获取所有申请记录（待审核、通过、驳回）
	@Select("select * from apply_info")
	public List<ApplyList> getApplyList();

	//提交申请，插入物品申请记录
	@Insert("INSERT INTO apply_info (apply_user_id, apply_user_truename, apply_user_name, apply_product_id, apply_product_name, apply_num, apply_product_price, apply_time, apply_pass) " +
			"VALUES (#{user_id},#{user_truename},#{user_name},#{product_id},#{product_name},#{num},#{price},#{time},0)")
	public int addProductApply(int user_id, String user_truename, String user_name, int product_id, String product_name, int num, float price, String time);

//	//查询申请状态（apply_pass:0、1、2;待审核、通过、驳回）
//	@Select("SELECT * FROM apply_info WHERE apply_pass=0")
//	public int getApplyStatusById(int apply_id);
//
//	//查询申请人ID(待审核状态的申请记录)
//	@Select("SELECT apply_user_id FROM apply_info WHERE apply_pass=0")
//	public int getApplyUserIdByApplyId();
//
//	//查询申请物品ID(待审核状态的申请记录)
//	@Select("SELECT apply_product_id FROM apply_info WHERE apply_pass=0")
//	public int getApplyProductIdByApplyId();

	//根据申请人姓名和物品名称模糊搜索申请记录（待审核的记录：pass=0）
	@Select("SELECT * FROM apply_info WHERE apply_pass=0 AND (apply_user_truename LIKE #{search} OR apply_product_name LIKE #{search})")
	public List<ApplyList> getApplyCheckByUserNameAndProductName(String search);

	//根据申请人姓名和物品名称模糊搜索申请记录（所有记录）
	@Select("SELECT * FROM apply_info WHERE apply_user_truename LIKE #{search} OR apply_product_name LIKE #{search}")
	public List<ApplyList> getApplyListByUserNameAndProductName(String search);

	//通过申请ID对申请审核情况进行修改(修改审核情况，审核人，审核时间)
	@Update("UPDATE apply_info SET apply_pass=#{apply_pass},apply_passman_id=#{passman_id},apply_passman_name=#{passman_name},apply_time=#{time} WHERE apply_id=#{apply_id}")
	public int updateApplyCheckById(int apply_pass,int passman_id,String passman_name,String time,int apply_id);

	//通过申请ID删除申请记录
	@Delete("DELETE FROM apply_info WHERE apply_id=#{id}")
	public int deleteApplyById(int id);
}

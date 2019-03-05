package com.office.manage.domain;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BorrowListMapper {

	/*
	 * 统计用户借入的所有物品数量
	 * */
	@Select("SELECT SUM(borrowinfo_num) FROM borrow_info WHERE borrowinfo_return=0")
	public int getAllProductNumData();

	/*
	 * 统计用户丢失的所有物品数量
	 * */
	@Select("SELECT SUM(borrowinfo_num) FROM borrow_info WHERE borrowinfo_return=1")
	public int getAllMissProductNumData();

	/*
	 * 统计借给用户物品的资金（0=借出，1=归还，2=消耗遗失）
	 * */
	@Select("SELECT SUM(borrowinfo_num*borrowinfo_product_price) FROM borrow_info WHERE borrowinfo_return=#{status} ")
	public int getBorrowProductMoneyData(int status);

	/*
	 * 统计用户借用物品类型的数据（0=借出，1=归还，2=消耗遗失）
	 * */
	@Select("SELECT" +
			" SUM(b.borrowinfo_num) " +
			"FROM " +
			" ( " +
			"  SELECT " +
			"   b.borrowinfo_num, " +
			"   t.type_category " +
			"   FROM " +
			"   ( " +
			"     SELECT " +
			"     borrow_info.borrowinfo_product_name, " +
			"     borrow_info.borrowinfo_num, " +
			"     borrow_info.borrowinfo_return, " +
			"     product_info.product_type " +
			"     FROM " +
			"     borrow_info " +
			"     LEFT JOIN product_info ON borrow_info.borrowinfo_product_name = product_info.product_name " +
			"     WHERE " +
			"     borrowinfo_return = #{status} " +
			"     ) AS b " +
			"   LEFT JOIN type_info AS t ON b.product_type = t.product_type " +
			"   WHERE " +
			"   b.borrowinfo_return = #{status} " +
			"  ) AS b " +
			"WHERE " +
			"  b.type_category = #{category}")
	public Integer getBorrowProductTypeData(int status, String category);




	//获取所有借入记录
	@Select("select * from borrow_info")
	public List<BorrowList> getAllBorrowList();

	//获取用户对应的借入记录
	@Select("SELECT * FROM borrow_info WHERE borrowinfo_user_id=#{user_id}")
	public List<BorrowList> getBorrowListById(int user_id);

	@Select("SELECT * FROM borrow_info WHERE borrowinfo_id=#{id}")
	public BorrowList getBorrowById(int id);

	//申请通过后插入借入记录
	@Insert("INSERT INTO borrow_info " +
			"(borrowinfo_user_id, borrowinfo_user_truename, borrowinfo_product_id, borrowinfo_product_name, " +
			"borrowinfo_num, borrowinfo_product_price, borrowinfo_time,borrowinfo_return,borrowinfo_return_num,borrowinfo_missprice)" +
			"VALUES (#{user_id},#{user_truename},#{product_id},#{product_name},#{num},#{price},#{time},0,0,0)")
	public int addBorrowList(int user_id, String user_truename, int product_id, String product_name, int num, float price, String time);

	//根据物品名模糊搜索借入记录
	@Select("SELECT * FROM borrow_info WHERE borrowinfo_product_name LIKE #{search} OR borrowinfo_user_truename LIKE #{search}")
	public List<BorrowList> searchBorrowById(String search);

	//根据记录ID删除借入归还记录
	@Delete("DELETE FROM borrow_info WHERE borrowinfo_id=#{id}")
	public int deleteBorrowById(int id);

	//借出记录归还挂失处理
	@Update("UPDATE borrow_info SET " +
			"borrowinfo_return = #{b_return}," +
			" borrowinfo_return_num = #{num}," +
			" borrowinfo_missprice = #{missprice}," +
			" borrowinfo_time = #{time}" +
			"WHERE borrowinfo_id = #{id}")
	public int updateBorrowById(int b_return, int num, float missprice, String time, int id);

}

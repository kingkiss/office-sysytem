package com.office.manage.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BorrowListMapper {

	//获取所有借入记录
	@Select("select * from borrow_info")
	public List<BorrowList> getAllBorrowList();

	//获取用户对应的借入记录
	@Select("SELECT * FROM borrow_info WHERE borrowinfo_user_id=#{user_id}")
	public List<BorrowList> getBorrowListById(int user_id);


	//申请通过后插入借入记录
	@Insert("INSERT INTO borrow_info " +
			"(borrowinfo_user_id, borrowinfo_user_truename, borrowinfo_product_id, borrowinfo_product_name, " +
			"borrowinfo_num, borrowinfo_product_price, borrowinfo_time,borrowinfo_return,borrowinfo_return_num,borrowinfo_missprice)" +
			"VALUES (#{user_id},#{user_truename},#{product_id},#{product_name},#{num},#{price},#{time},0,0,0)")
	public int addBorrowList(int user_id, String user_truename, int product_id, String product_name, int num, float price, String time);

	//根据物品名模糊搜索借入记录
	@Select("SELECT * FROM borrow_info WHERE borrowinfo_product_name LIKE #{search}")
	public List<BorrowList> searchBorrowById(String search);

}

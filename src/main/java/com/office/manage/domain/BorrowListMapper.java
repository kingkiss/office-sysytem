package com.office.manage.domain;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BorrowListMapper {

	@Select("select * from borrow_info")
	public BorrowList getAllBorrowList();

	//申请通过后插入借入记录
	@Insert("INSERT INTO borrow_info " +
			"(borrowinfo_user_id, borrowinfo_user_truename, borrowinfo_product_id, borrowinfo_product_name, " +
			"borrowinfo_num, borrowinfo_product_price, borrowinfo_time,borrowinfo_return)" +
			"VALUES (#{user_id},#{user_truename},#{product_id},#{product_name},#{num},#{price},#{time},0)")
	public int addBorrowList(int user_id, String user_truename, int product_id, String product_name, int num, float price, String time);

}

package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {

    //根据type_info表中type_category的值来查询product_type子分类
    @Select("SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type WHERE type_category=#{typeCategory}")
    public List<Product> getProductByTypeCategory(String typeCategory);

    //根据物品名和物品类型进行模糊搜索
    @Select("SELECT * FROM (SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type " +
            "WHERE type_category=#{type} )product_info WHERE product_name LIKE #{searchP} OR product_type LIKE #{searchP}")
    public List<Product> getProductByNameAndType(String type,String searchP);

    //根据物品ID搜索物品库存
    @Select("SELECT product_num FROM product_info WHERE product_id=#{product_id}")
    public int getProductNumById(int product_id);

    //申请物品成功后，减少对应的库存
    @Update("UPDATE product_info SET product_num=product_num-#{apply_num} WHERE product_id=#{product_id}")
    public int updataProductNum(int apply_num,int product_id);



}

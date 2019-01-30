package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {

    //根据type_info表中type_category的值来查询product_type子分类
    @Select("SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type WHERE type_category=#{typeCategory}")
    public List<Product> getProductByTypeCategory(String typeCategory);

    @Select("SELECT * FROM (SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type " +
            "WHERE type_category=#{type} )product_info WHERE product_name LIKE #{searchP} OR product_type LIKE #{searchP}")
    public List<Product> getProductByNameAndType(String type,String searchP);


}

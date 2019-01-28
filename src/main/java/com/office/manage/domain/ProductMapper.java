package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {

    //根据type_info表中type_category的值来查询product_type子分类
    @Select("SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type WHERE type_category=#{typeCategory}")
    public Product getProductByTypeCategory(String typeCategory);
}

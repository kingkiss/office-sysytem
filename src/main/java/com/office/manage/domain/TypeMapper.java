package com.office.manage.domain;

import org.apache.ibatis.annotations.Select;

public interface TypeMapper {

    //根据一级分类搜索二级分类
    @Select("SELECT product_type FROM type_info WHERE type_category=#{typeCategory}")
    public String getProductTypeByTypeCategory(String typeCategory);
}

package com.office.manage.domain;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TypeMapper {

    //根据一级分类搜索二级分类
    @Select("SELECT product_type FROM type_info WHERE type_category=#{typeCategory}")
    public String getProductTypeByTypeCategory(String typeCategory);

    //搜索所有类型
    @Select("SELECT product_type FROM type_info")
    public List<String> getAllType();

}

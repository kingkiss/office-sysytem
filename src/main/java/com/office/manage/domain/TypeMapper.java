package com.office.manage.domain;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    //根据类型搜索分类
    @Select("SELECT type_category FROM type_info WHERE product_type=#{product_type}")
    public String getCategoryByType(String product_type);

    //搜索所有类型
    @Select("SELECT product_type FROM type_info")
    public List<String> getAllType();

    //获取整个类型表
    @Select("SELECT * FROM type_info")
    public List<Typelist> getTypeInfo();

    //获取类型根据分类
    @Select("SELECT * FROM type_info WHERE type_category=#{type_category}")
    public List<Typelist> getTypeInfoByCategory(String type_category);

    //添加新类型
    @Insert("INSERT INTO type_info (type_category,product_type) VALUES (#{type_category},#{product_type})")
    public int addNewType(String type_category,String product_type);

    //删除类型根据ID
    @Delete("DELETE FROM type_info WHERE type_id=#{type_id}")
    public int deleteType(String type_id);




}

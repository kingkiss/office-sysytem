package com.office.manage.domain;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductMapper {
    /*
    * 统计仓库内所有物品数量
    * */
    @Select("SELECT SUM(product_num) FROM product_info")
    public int getAllProductNumData();

    /*
     * 统计仓库内所有物品资金
     * */
    @Select("SELECT SUM(product_num*product_price) FROM product_info ")
    public int getAllProductMoneyData();


    //查询所有物品
    @Select("SELECT * FROM product_info")
    public List<Product> getAllProduct();

    //从所有物品中搜索
    @Select("SELECT * FROM product_info WHERE product_name LIKE #{search} OR product_type LIKE #{search}")
    public List<Product> getProductFromAllProduct(String search);

    //根据type_info表中type_category的值来查询product_type子分类
    @Select("SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type WHERE type_category=#{typeCategory}")
    public List<Product> getProductByTypeCategory(String typeCategory);

    //根据物品名和物品类型进行模糊搜索
    @Select("SELECT * FROM (SELECT product_info.* FROM product_info LEFT JOIN type_info on product_info.product_type=type_info.product_type " +
            "WHERE type_category=#{type} )product_info WHERE product_name LIKE #{searchP} OR product_type LIKE #{searchP}")
    public List<Product> getProductByNameAndType(String type,String searchP);

    //根据物品ID查询物品库存
    @Select("SELECT product_num FROM product_info WHERE product_id=#{product_id}")
    public int getProductNumById(int product_id);

    //根据物品ID查询物品名称
    @Select("SELECT product_name FROM product_info WHERE product_id=#{product_id}")
    public String getProductNameById(int product_id);

    //修改物品信息
    @Update("UPDATE product_info SET product_name=#{p_name}, product_num=#{p_num}, product_price=#{p_price}, product_type=#{p_type} WHERE product_id=#{p_id}")
    public int updataProduct(String p_name, int p_num ,float p_price, String p_type, int p_id);

    //申请物品成功后，减少对应的库存
    @Update("UPDATE product_info SET product_num=product_num-#{apply_num} WHERE product_id=#{product_id}")
    public int updataProductNum(int apply_num,int product_id);

    //申请物品驳回后，增加对应的库存
    @Update("UPDATE product_info SET product_num=product_num+#{apply_num} WHERE product_id=#{product_id}")
    public int updataProductNumA(int apply_num,int product_id);

    //添加新物品
    @Insert("INSERT INTO product_info (product_name,product_num,product_price,product_type) VALUES (#{product_name},#{product_num},#{product_price},#{product_type})")
    public int addNewProduct(String product_name,int product_num ,float product_price,String product_type);

    //删除物品
    @Delete("DELETE FROM product_info WHERE product_id=#{product_id}")
    public int deleteProductByid(int product_id);


}

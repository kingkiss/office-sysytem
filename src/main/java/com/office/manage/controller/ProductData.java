package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductData {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    TypeMapper typeMapper;

    //获取所有物品
    @RequestMapping(value = "/allProduct",method = RequestMethod.GET)
    public Map<String,Object> getAllProductList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                       @RequestParam(value = "orderBy" ,defaultValue = "product_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<Product> products = productMapper.getAllProduct();
        PageInfo<Product> page = new PageInfo<>(products);
        Map<String,Object> m = new HashMap<>();
        m.put("AllProducts",products);
        m.put("page",page);
        return m;

    }

    //获取所有物品类型
    @RequestMapping(value = "/allType",method = RequestMethod.GET)
    public Map<String,Object> getAllType(){
        List<String> Types = typeMapper.getAllType();
        Map<String,Object> m = new HashMap<>();
        m.put("AllType",Types);
        return m;
    }

    //获取类型信息
    @RequestMapping(value = "/TypeInfo",method = RequestMethod.GET)
    public Map<String,Object> getType(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                      @RequestParam(value = "orderBy" ,defaultValue = "type_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<Typelist> types = typeMapper.getTypeInfo();
        PageInfo<Typelist> page = new PageInfo<>(types);
        Map<String,Object> m = new HashMap<>();
        m.put("TypeInfos",types);
        m.put("page",page);
        return m;

    }

    //获取物品类型
    @RequestMapping(value = "/typeCategory",method = RequestMethod.GET)
    public Map<String,Object> getTypeCategory(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                               @RequestParam(value = "category",defaultValue = "") String category){
        PageHelper.startPage(start,size);
        List<Typelist> types = typeMapper.getTypeInfoByCategory(category);
        PageInfo<Typelist> Searchpage = new PageInfo<>(types);
        Map<String,Object> su = new HashMap<>();
        su.put("typeCategory",types);
        su.put("page",Searchpage);
        return su;
    }


    //搜索物品（用名称和类型，type是大的分类）
    @RequestMapping(value = "/SearchProduct",method = RequestMethod.GET)
    public Map<String,Object> getSearchProduct(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                                      @RequestParam(value = "product",defaultValue = "") String product){
        String search = "%"+product+"%";
        PageHelper.startPage(start,size);
        List<Product> SearchProducts = productMapper.getProductFromAllProduct(search);
        PageInfo<Product> Searchpage = new PageInfo<>(SearchProducts);
        Map<String,Object> su = new HashMap<>();
        su.put("SearchProduct",SearchProducts);
        su.put("page",Searchpage);
        return su;
    }


    //获取文具类物品数据
    @RequestMapping(value = "/allStationery",method = RequestMethod.GET)
    public Map<String,Object> getStationeryProductList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                          @RequestParam(value = "orderBy" ,defaultValue = "product_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<Product> products = productMapper.getProductByTypeCategory("文具事务用品");
        PageInfo<Product> page = new PageInfo<>(products);
        Map<String,Object> m = new HashMap<>();
        m.put("products",products);
        m.put("page",page);
        return m;

    }

    //获取办公消耗品数据
    @RequestMapping(value = "/allConsumable",method = RequestMethod.GET)
    public Map<String,Object> getConsumableProductList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                       @RequestParam(value = "orderBy" ,defaultValue = "product_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<Product> products = productMapper.getProductByTypeCategory("办公耗材");
        PageInfo<Product> page = new PageInfo<>(products);
        Map<String,Object> m = new HashMap<>();
        m.put("products",products);
        m.put("page",page);
        return m;

    }

    //获取办公设备数据
    @RequestMapping(value = "/allEquipment",method = RequestMethod.GET)
    public Map<String,Object> getEquipmentProductList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size,
                                                       @RequestParam(value = "orderBy" ,defaultValue = "product_id asc") String orderBy){
        PageHelper.startPage(start,size,orderBy);
        List<Product> products = productMapper.getProductByTypeCategory("办公设备");
        PageInfo<Product> page = new PageInfo<>(products);
        Map<String,Object> m = new HashMap<>();
        m.put("products",products);
        m.put("page",page);
        return m;

    }

    //搜索物品（用名称和类型，type是大的分类）
    @RequestMapping(value = "/SearchOffice",method = RequestMethod.GET)
    public Map<String,Object> getSearchStationeryList(@RequestParam(value = "start",defaultValue = "1") int start , @RequestParam(value = "size" ,defaultValue = "15") int size ,
                                                @RequestParam(value = "product",defaultValue = "") String product ,@RequestParam(value = "type") String type){
        String search = "%"+product+"%";
        PageHelper.startPage(start,size);
        List<Product> SearchProducts = productMapper.getProductByNameAndType(type,search);
        PageInfo<Product> Searchpage = new PageInfo<>(SearchProducts);
        Map<String,Object> su = new HashMap<>();
        su.put("SearchProduct",SearchProducts);
        su.put("page",Searchpage);
        return su;
    }

    //添加新物品
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public Message addNewProduct(@RequestBody Product product){
        Message msg = new Message();
        int result = productMapper.addNewProduct(product.getProduct_name(),product.getProduct_num(),product.getProduct_price(),product.getProduct_type());
        if(result == 1){
            msg.setResult(true);
            msg.setInfo("成功添加物品");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误！请重试");
            return msg;
        }
    }

    //删除物品
    @RequestMapping(value = "/deleteProduct/{product_id}",method = RequestMethod.DELETE)
    public Message deleteProduct(@PathVariable String product_id){
        Message msg = new Message();
        int result = productMapper.deleteProductByid(product_id);
        //System.out.println(result);
        if( result>0 ){
            msg.setResult(true);
            msg.setInfo("已删除"+result+"个物品");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误请重试");
            return msg;
        }
    }



}

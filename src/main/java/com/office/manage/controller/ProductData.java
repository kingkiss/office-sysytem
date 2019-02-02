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


}

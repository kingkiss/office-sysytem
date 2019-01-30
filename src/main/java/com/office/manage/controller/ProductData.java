package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.Product;
import com.office.manage.domain.ProductMapper;
import com.office.manage.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductData {

    @Autowired
    ProductMapper productMapper;

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


}

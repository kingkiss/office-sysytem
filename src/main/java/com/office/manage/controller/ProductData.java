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




}

package com.office.manage.controller;


import com.office.manage.domain.ApplyListMapper;
import com.office.manage.domain.BorrowListMapper;
import com.office.manage.domain.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UseProductData {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ApplyListMapper applyListMapper;
    @Autowired
    BorrowListMapper borrowListMapper;

    //统计所以去向物品的数量
    @RequestMapping(value = "/useProductData", method = RequestMethod.GET)
    public Map<String,Object> getAllProductData(){
        Map<String,Object> data = new HashMap<>();
        //库存所有物品数量
        int storeData = productMapper.getAllProductNumData();
        //待审核所有物品数量
        int applyData = applyListMapper.getAllProductNumData();
        //借入未归还物品数量
        int borrowData = borrowListMapper.getAllProductNumData();
        //丢失物品数量
        int missData = borrowListMapper.getAllMissProductNumData();

        data.put("storeData",storeData);
        data.put("applyData",applyData);
        data.put("borrowData",borrowData);
        data.put("missData",missData);
        return data;
    }

    //统计物品资金的去向
    @RequestMapping(value = "/productMoneyData", method = RequestMethod.GET)
    public Map<String,Object> getlProductMoneyData() {
        Map<String, Object> data = new HashMap<>();
        //库存物品资金
        float storeM = productMapper.getAllProductMoneyData();
        //用户借出物品的资金
        float borrowM = borrowListMapper.getBorrowProductMoneyData(0);
        //用户消耗遗失物品的资金
        float missM = borrowListMapper.getBorrowProductMoneyData(2);
        //待审核物品的资金
        float applyM = applyListMapper.getApplyProductMoneyData();
        //总资金
        float totalMoney = storeM + borrowM + missM + applyM;

        data.put("storeM",storeM);
        data.put("borrowM",borrowM);
        data.put("missM",missM);
        data.put("applyM",applyM);
        data.put("totalMoney",totalMoney);
        return data;
    }
}

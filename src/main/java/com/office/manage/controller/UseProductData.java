package com.office.manage.controller;


import com.office.manage.domain.ApplyListMapper;
import com.office.manage.domain.BorrowListMapper;
import com.office.manage.domain.ProductMapper;
import com.office.manage.domain.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UseProductData {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ApplyListMapper applyListMapper;
    @Autowired
    BorrowListMapper borrowListMapper;
    @Autowired
    TypeMapper typeMapper;

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

    //用户借用物品类型统计
    @RequestMapping(value = "/ProductTypeData", method = RequestMethod.GET)
    public Map<String,Object> getProductTypeData(){
        Map<String,Object> data = new HashMap<>();
        //文具事务用品（使用中=0）
        Integer stationery0 = borrowListMapper.getBorrowProductTypeData(0,"文具事务用品");
        //文具事务用品（已归还=1）
        Integer stationery1 = borrowListMapper.getBorrowProductTypeData(1,"文具事务用品");
        //文具事务用品（消耗遗失=2）
        Integer stationery2 = borrowListMapper.getBorrowProductTypeData(2,"文具事务用品");

        //办公耗材（使用中=0）
        Integer consumable0 = borrowListMapper.getBorrowProductTypeData(0,"办公耗材");
        //办公耗材（已归还=1）
        Integer consumable1 = borrowListMapper.getBorrowProductTypeData(1,"办公耗材");
        //办公耗材（消耗遗失=2）
        Integer consumable2 = borrowListMapper.getBorrowProductTypeData(2,"办公耗材");

        //办公设备（使用中=0）
        Integer equipment0 = borrowListMapper.getBorrowProductTypeData(0,"办公设备");
        //办公设备（已归还=1）
        Integer equipment1 = borrowListMapper.getBorrowProductTypeData(1,"办公设备");
        //办公设备（消耗遗失=2）
        Integer equipment2 = borrowListMapper.getBorrowProductTypeData(2,"办公设备");

        if(stationery0 != null){
            data.put("stationery0",stationery0.intValue());
        }else {
            data.put("stationery0",0);
        }
        if(stationery1 != null){
            data.put("stationery1",stationery1.intValue());
        }else {
            data.put("stationery1",0);
        }
        if(stationery2 != null){
            data.put("stationery2",stationery2.intValue());
        }else {
            data.put("stationery2",0);
        }

        if(consumable0 != null){
            data.put("consumable0",consumable0.intValue());
        }else {
            data.put("consumable0",0);
        }
        if(consumable1 != null){
            data.put("consumable1",consumable1.intValue());
        }else {
            data.put("consumable1",0);
        }
        if(consumable2 != null){
            data.put("consumable2",consumable2.intValue());
        }else {
            data.put("consumable2",0);
        }

        if(equipment0 != null){
            data.put("equipment0",equipment0.intValue());
        }else {
            data.put("equipment0",0);
        }
        if(equipment1 != null){
            data.put("equipment1",equipment1.intValue());
        }else {
            data.put("equipment1",0);
        }
        if(equipment2 != null){
            data.put("equipment2",equipment2.intValue());
        }else {
            data.put("equipment2",0);
        }

        /*data.put("stationery0",stationery0);
        data.put("stationery1",stationery1);
        data.put("stationery2",stationery2);
        data.put("consumable0",consumable0);
        data.put("consumable1",consumable1);
        data.put("consumable2",consumable2);
        data.put("equipment0",equipment0);
        data.put("equipment1",equipment1);
        data.put("equipment2",equipment2);*/
        return data;
    }

    //一周用品类型申请统计
    @RequestMapping(value = "/ApplyTypeNumData", method = RequestMethod.GET)
    public Map<String,Object> getApplyTypeNumData(){
        //总数据
        Map<String,Object> data = new HashMap<>();
        //文具事务用品map类
        List<Object> stationeryData = new ArrayList<>();
        //办公耗材map类
        List<Object> consumableData = new ArrayList<>();
        //办公设备
        List<Object> equipmentData = new ArrayList<>();

        //获取7天内订单是日期
        List<String> dateTime = applyListMapper.getApplyTime();
        //获取文具事务用品分类的申请数量
        for (String t :dateTime){
            Integer stationery = applyListMapper.getApplyTypeNumByTypeAndTime("文具事务用品",t);
            if(stationery != null){
                stationeryData.add(stationery.intValue());
            }else {
                stationeryData.add(0);
            }
        }
        //获取办公耗材分类的申请数量
        for (String t :dateTime){
            Integer consumable = applyListMapper.getApplyTypeNumByTypeAndTime("办公耗材",t);
            if(consumable != null){
                consumableData.add(consumable.intValue());
            }else {
                consumableData.add(0);
            }
        }
        //获取办公设备的申请数量
        for (String t :dateTime){
            Integer equipment = applyListMapper.getApplyTypeNumByTypeAndTime("办公设备",t);
            if(equipment != null){
                equipmentData.add(equipment.intValue());
            }else {
                equipmentData.add(0);
            }
        }

        data.put("stationeryData",stationeryData);
        data.put("consumableData",consumableData);
        data.put("equipmentData",equipmentData);
        data.put("week",dateTime);
        return  data;
    }



}

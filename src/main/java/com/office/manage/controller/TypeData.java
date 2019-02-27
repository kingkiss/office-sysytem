package com.office.manage.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.office.manage.domain.Message;
import com.office.manage.domain.Product;
import com.office.manage.domain.TypeMapper;
import com.office.manage.domain.Typelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TypeData {
    @Autowired
    TypeMapper typeMapper;

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
        if(types.size() != 0){
            m.put("testcase",true);
        }else {
            m.put("testcase",false);
        }
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
        if(types.size() != 0){
            su.put("testcase",true);
        }else {
            su.put("testcase",false);
        }
        return su;
    }

    //增加物品类型
    @RequestMapping(value = "/addType",method = RequestMethod.POST)
    public Message addNewProduct(@RequestBody Typelist type){
        Message msg = new Message();
        int result = typeMapper.addNewType(type.getType_category(),type.getProduct_type());
        if(result == 1){
            msg.setResult(true);
            msg.setInfo("成功添加类型");
            return msg;
        }else {
            msg.setResult(false);
            msg.setInfo("未知错误！请重试");
            return msg;
        }
    }

    //删除物品类型
    @RequestMapping(value = "/deleteType/{type_id}",method = RequestMethod.DELETE)
    public Message deleteProduct(@PathVariable String type_id) {
        Message msg = new Message();
        int result = typeMapper.deleteType(type_id);
        //System.out.println(result);
        if (result > 0) {
            msg.setResult(true);
            msg.setInfo("已删除" + result + "个类型");
            return msg;
        } else {
            msg.setResult(false);
            msg.setInfo("未知错误请重试");
            return msg;
        }
    }
}

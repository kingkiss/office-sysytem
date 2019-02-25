package com.office.manage.service;


import com.office.manage.domain.BorrowList;
import com.office.manage.domain.BorrowListMapper;
import com.office.manage.domain.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BorrowServiceImpl implements BorrowService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    BorrowListMapper borrowListMapper;

    //借入记录挂失
    public boolean missBorrow(BorrowList borrowList) throws Exception {
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        int result = borrowListMapper.updateBorrowById(2,borrowList.getBorrowinfo_return_num(),borrowList.getBorrowinfo_missprice(),datetime,borrowList.getBorrowinfo_id());
        if(result == 1){
            return true;
        }else {
            return  false;
        }
    }

    //归还
    @Transactional
    public boolean returnBorrow(BorrowList borrowList) throws Exception{
        Date time = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = format.format(time);
        int result = borrowListMapper.updateBorrowById(1,borrowList.getBorrowinfo_return_num(),borrowList.getBorrowinfo_missprice(),datetime,borrowList.getBorrowinfo_id());
        if(result == 1){
            int resultP = productMapper.updataProductNumA(borrowList.getBorrowinfo_return_num(),borrowList.getBorrowinfo_product_id());
            if( resultP == 1 ){
                return true;
            }else {
                return false;
            }
        }else {
           return false;
        }
    }
}

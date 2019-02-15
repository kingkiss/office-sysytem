package com.office.manage.service;


import com.office.manage.domain.BorrowList;

public interface BorrowService {
    //借入记录挂失
    public boolean missBorrow(BorrowList borrowList) throws Exception;

    //归还
    public boolean returnBorrow(BorrowList borrowList) throws Exception;
}

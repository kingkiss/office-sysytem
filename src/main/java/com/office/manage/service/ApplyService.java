package com.office.manage.service;

import com.office.manage.domain.ApplyList;

public interface ApplyService {

    //提交申请事务
    public boolean submitProductApply(ApplyList applyList) throws Exception;

    //修改申请审核事务
    public boolean updataApplyCheck(ApplyList applyList) throws Exception;


}

package com.office.manage.service;

import com.office.manage.domain.ApplyList;

public interface ApplyService {

    //提交申请事务
    public boolean submitProductApply(ApplyList applyList) throws Exception;
}

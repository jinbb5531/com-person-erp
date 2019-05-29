package com.person.erp.order.service.impl;

import com.person.erp.order.dao.IOrderOperateDAO;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.service.IOrderOperateService;
import org.springframework.stereotype.Service;

@Service
public class OrderOperateServiceImpl implements IOrderOperateService {

    private IOrderOperateDAO dao;

    @Override
    public boolean insert(OrderOperate orderOperate) {
        return dao.insert(orderOperate) > 0;
    }
}

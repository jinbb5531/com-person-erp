package com.person.erp.order.service.impl;

import com.person.erp.order.constant.OrderConstant;
import com.person.erp.order.dao.IOrderOperateDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.model.OrderDTO;
import com.person.erp.order.service.IOrderOperateService;
import com.person.erp.order.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class OrderOperateServiceImpl implements IOrderOperateService {
    @Resource
    private IOrderOperateDAO dao;
    @Resource
    private IOrderService orderService;

    @Override
    public boolean insert(OrderOperate orderOperate, OrderDTO order) {
        long insert = dao.insert(orderOperate);
        boolean success = orderService.updateOrder(order);
        return insert > 0 && success ? true : false;
    }
}

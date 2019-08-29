package com.person.erp.order.service.impl;

import com.person.erp.identity.model.UserDTO;
import com.person.erp.order.dao.IOrderOperateDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.service.IOrderOperateService;
import com.person.erp.order.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderOperateServiceImpl implements IOrderOperateService {
    @Resource
    private IOrderOperateDAO dao;
    @Resource
    private IOrderService orderService;

    @Override
    public boolean insert(OrderOperate orderOperate, Order order) {
        long insert = dao.insert(orderOperate);
        boolean success = orderService.updateStatus(order);
        return insert > 0 && success;
    }

    @Override
    public OrderOperate getNum(String orderCode, long systemTag) {
        return dao.getCutNum(orderCode, systemTag);
    }

    @Override
    public boolean insert(OrderOperate orderOperate) {
        return dao.insert(orderOperate) > 0 ;
    }

    @Override
    public List<OrderOperate> sumCutNumGroupByUserList(List<UserDTO> dtoList, Date startTime, Date endTime) {
        return dao.sumCutNumGroupByUserList(dtoList, startTime, endTime);
    }
}

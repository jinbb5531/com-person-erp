package com.person.erp.analyze.service.impl;

import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.entity.Order;

import javax.annotation.Resource;
import java.util.List;

public class AnlayzeServiceImpl implements IAnlayzeService {
    @Resource
    IOrderDAO orderDAO;
    @Override
    public List<Order> getPlanNumber(Long startDate, Long endDate) {
        List<Order> orders = orderDAO.planNumber(startDate, endDate);
        return orders;
    }
}

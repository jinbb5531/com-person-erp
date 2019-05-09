package com.person.erp.order.service.impl;

import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.dao.IOrderItemDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单实现类
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDAO dao;
    @Resource
    private IOrderItemDAO itemDAO;

    @Override
    public boolean createOrder(Order order) {
        return dao.insert(order) > 0;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return false;
    }

    @Override
    public boolean updateOrder(Order order) {
        return dao.update(order) > 0;
    }

    @Override
    public Order findById(Order order) {
        return null;
    }

    @Override
    public List<Order> findPage(Order order) {
        return null;
    }
}
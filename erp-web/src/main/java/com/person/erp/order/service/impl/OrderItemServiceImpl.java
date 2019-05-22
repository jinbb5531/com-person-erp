package com.person.erp.order.service.impl;

import com.person.erp.order.dao.IOrderItemDAO;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.service.IOrderItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单明细业务类
 */
@Service
@Transactional
public class OrderItemServiceImpl implements IOrderItemService {

    @Resource
    private IOrderItemDAO dao;

    @Override
    public boolean insert(OrderItem orderItem) {
        return dao.insert(orderItem) > 0;
    }

    @Override
    public boolean delete(OrderItem orderItem) {
        return false;
    }

    @Override
    public boolean update(OrderItem orderItem) {
        return dao.update(orderItem) > 0;
    }

    @Override
    public boolean insertBatch(List<OrderItem> itemList) {
        return dao.insertBatch(itemList) > 0;
    }

    @Override
    public boolean deleteByOrderCode(String orderCode) {
        return dao.deleteByOrderCode(orderCode) >= 0;
    }

    @Override
    public boolean deleteBatch(String...codes) {
        return dao.deleteByIds(codes) >=0;
    }
}

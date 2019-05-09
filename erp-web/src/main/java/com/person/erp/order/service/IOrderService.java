package com.person.erp.order.service;

import com.person.erp.order.entity.Order;

import java.util.List;

/**
 * 订单服务接口
 */
public interface IOrderService {

    /**
     * 新增订单
     */
    boolean createOrder(Order order);

    /**
     * 删除订单
     */
    boolean deleteOrder(Order order);

    /**
     * 修改订单
     */

    boolean updateOrder(Order order);

    /**
     * 订单查询
     */
    Order findById(Order order);

    /**
     * 条件分页查询订单
     */
    List<Order> findPage(Order order);

}

package com.person.erp.order.service;

import com.github.pagehelper.PageInfo;
import com.person.erp.order.entity.Order;
import com.person.erp.order.model.OrderDTO;

import java.util.List;

/**
 * 订单服务接口
 */
public interface IOrderService {

    /**
     * 新增订单
     */
    boolean createOrder(OrderDTO order);

    /**
     * 删除订单
     */
    boolean deleteOrder(Order order);

    /**
     * 修改订单
     */

    boolean updateOrder(OrderDTO order);

    /**
     * 更新订单状态
     */
    boolean updateStatus(Order order);

    /**
     * 订单查询
     */
    Order findById(Order order);

    /**
     * 批量删除订单
     * @param codes
     */
    boolean deleteBatch(String[] codes);

    /**
     * 条件分页查询订单
     * @param order
     * @param page
     * @return
     */
    PageInfo<Order>findPage(Order order, PageInfo<Order> page);
}

package com.person.erp.order.service;

import com.person.erp.order.entity.OrderItem;

import java.util.List;

/**
 * 订单明细
 */
public interface IOrderItemService {

    /**
     * 插入明细
     * @param orderItem
     * @return
     */
    boolean insert(OrderItem orderItem);

    boolean delete(OrderItem orderItem);

    boolean update(OrderItem orderItem);

    /**
     * 批量插入明细
     * @param itemList
     */
    boolean insertBatch(List<OrderItem> itemList);

    /**
     * 根据orderCode删除明细
     * @param orderCode
     * @return
     */
    boolean deleteByOrderCode(String orderCode);

    boolean deleteBatch(String... codes);
}

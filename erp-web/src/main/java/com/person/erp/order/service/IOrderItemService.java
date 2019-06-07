package com.person.erp.order.service;

import com.person.erp.order.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 根据orderCode批量删除明细
     * @param orderCodes
     * @return
     */
    boolean deleteByOrderCodeBatch(@Param("array") String[]orderCodes, @Param("systemTag") long systemTag);

    /**
     * 批量删除订单明细
     * @param codes
     * @return
     */
    boolean deleteBatch(String... codes);

    /**
     * 批量更新订单明细
     * @param itemList
     * @return
     */
    boolean updateBatch(List<OrderItem> itemList);
}

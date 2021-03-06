package com.person.erp.order.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单明细
 */
@Mapper
public interface IOrderItemDAO extends BaseDao<OrderItem> {
    /**
     * 删除订单下的订单明细
     * @param orderCode
     * @return
     */
     int deleteByOrderCode(@Param("orderCode") String orderCode,@Param("systemTag") long systemTag);

    /**
     * 批量删除订单下的订单明细
     * @param orderCodes
     * @return
     */
     int deleteByOrderCodeBatch(@Param("array") String[] orderCodes, @Param("systemTag") long systemTag);

    /**
     * 批量跟新订单明细
     * @param itemList
     * @return
     */
     int updateBatch(List<OrderItem> itemList);
}

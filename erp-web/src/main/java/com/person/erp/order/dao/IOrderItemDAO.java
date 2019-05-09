package com.person.erp.order.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.order.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单明细
 */
@Mapper
public interface IOrderItemDAO extends BaseDao<OrderItem> {
}
package com.person.erp.order.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单
 */
@Mapper
public interface IOrderDAO extends BaseDao<Order> {

    /**
     * 条件分页查询
     * @param order
     * @return
     */
    List<Order> findPage(Order order);
}

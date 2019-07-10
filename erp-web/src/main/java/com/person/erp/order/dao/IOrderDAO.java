package com.person.erp.order.dao;

import com.person.erp.order.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单
 */
@Mapper
public interface IOrderDAO  {

    /**
     * 新增订单
     * @param order
     * @return
     */
    int insert(Order order);

    /**
     * 删除订单
     * @param order
     * @return
     */
    int delete(Order order);

    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    int deleteBatch(@Param("array") String[] ids,@Param("systemTag") long systemTag);

    /**
     * 修改订单
     * @return
     */
    int update(Order order);

    /**
     * 查询单个订单，包含订单详情
     * @param order
     * @return
     */
    Order findById(Order order);

    /**
     * 条件分页查询订单
     * @param order
     * @return
     */
    List<Order> findPage(Order order);

    /**
     * 分页查询当前用户的订单
     * @param order
     * @return
     */
    List<Order> findPageByUser(Order order);

    /**
     * 获取订单的理想产量
     */
    List<Order>  planNumber(@Param(value = "startDate") Long startDate, @Param(value = "endDate") Long endDate);
}

package com.person.erp.order.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.order.entity.Order;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单
 */
@Mapper
public interface IOrderDAO  {

    /**
     * 条件分页查询
     * @param order
     * @return
     */
    List<Order> findPage(Order order);

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
    int deleteBatch(String ids);

    /**
     * 修改订单
     * @return
     */
    int update(Order order);
}

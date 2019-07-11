package com.person.erp.order.dao;

import com.person.erp.order.entity.OrderOperate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单操作
 */
@Mapper
public interface IOrderOperateDAO {

    /**
     * 插入订单操作数据
     * @param orderOperate
     * @return
     */
    int insert(OrderOperate orderOperate);

    /**
     * 获取实际产量
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 期间的每条对象
     */
    List<OrderOperate> realYiely(@Param(value = "startDate") Long startDate, @Param(value = "endDate") Long endDate, @Param(value = "systemTag") Long systemTag);
}

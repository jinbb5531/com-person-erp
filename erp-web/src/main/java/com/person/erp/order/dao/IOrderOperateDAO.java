package com.person.erp.order.dao;

import com.person.erp.order.entity.OrderOperate;
import org.apache.ibatis.annotations.Mapper;

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
}

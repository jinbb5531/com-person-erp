package com.person.erp.order.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.order.entity.OrderOperate;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作
 */
@Mapper
public interface IOrderOperateDAO extends BaseDao<OrderOperate> {
}

package com.person.erp.order.service;

import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.model.OrderDTO;

public interface IOrderOperateService {
    /**
     * 订单操作表
     * @param orderOperate
     * @return
     */
    boolean insert(OrderOperate orderOperate, OrderDTO order);

}

package com.person.erp.order.service;

import com.person.erp.order.entity.OrderOperate;

public interface IOrderOperateService {
    /**
     * 订单操作表
     * @param orderOperate
     * @return
     */
    boolean insert(OrderOperate orderOperate);

}

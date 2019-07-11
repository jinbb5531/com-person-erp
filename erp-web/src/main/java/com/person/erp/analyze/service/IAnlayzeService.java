package com.person.erp.analyze.service;

import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;

import java.util.List;

/**
 * 报表服务
 */
public interface IAnlayzeService {
    /**
     * 获取标准产量
     * @return
     */
    List<Order> getPlanYield(Long startDate, Long endDate, long systemTag);

    /**
     * 获取时间产量
     */
    List<OrderOperate> getRealYield(Long startDate, Long endDate, long systemTag);
}

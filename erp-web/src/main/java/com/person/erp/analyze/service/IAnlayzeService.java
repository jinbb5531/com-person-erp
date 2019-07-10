package com.person.erp.analyze.service;

import com.person.erp.order.entity.Order;

import java.util.List;

/**
 * 报表服务
 */
public interface IAnlayzeService {
    /**
     * 获取标准产量
     * @return
     */
    List<Order> getPlanNumber(Long startDate, Long endDate);
}

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
     * @param startDate
     * @param endDate
     * @param systemTag
     * @return
     */
    List<Order> getPlanYield(Long startDate, Long endDate, long systemTag);

    /**
     * 获取时间产量
     * @param startDate
     * @param endDate
     * @param systemTag
     * @return
     */
    List<OrderOperate> getRealYield(Long startDate, Long endDate, long systemTag);

    /**
     * 获取利润
     * @param startDate
     * @param endDate
     * @param systenTag
     * @return
     */
    List<Order> getProfit(Long startDate,Long endDate, long systenTag);
}

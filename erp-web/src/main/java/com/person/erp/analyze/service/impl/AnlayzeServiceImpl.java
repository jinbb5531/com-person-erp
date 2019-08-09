package com.person.erp.analyze.service.impl;

import com.person.erp.analyze.service.IAnlayzeService;
import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.dao.IOrderOperateDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AnlayzeServiceImpl implements IAnlayzeService {
    @Resource
    IOrderDAO orderDAO;
    @Resource
    IOrderOperateDAO operateDAO;
    @Override
    public List<Order> getPlanYield(Long startDate, Long endDate, long systemTag) {
        List<Order> orders = orderDAO.planNumber(startDate, endDate,systemTag);
        return orders;
    }

    @Override
    public List<OrderOperate> getRealYield(Long startDate, Long endDate, long systemTag) {
        List<OrderOperate> orderOperates = operateDAO.realYiely(startDate, endDate, systemTag);
        return orderOperates;
    }

    @Override
    public List<Order> getProfit(Long startDate, Long endDate, long systenTag) {
        return null;
    }
}

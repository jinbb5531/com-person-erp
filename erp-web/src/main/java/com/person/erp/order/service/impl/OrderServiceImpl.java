package com.person.erp.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.dao.IOrderItemDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单实现类
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private IOrderDAO dao;
    @Resource
    private IOrderItemDAO itemDAO;

    @Override
    public boolean createOrder(Order order) {
        return dao.insert(order) > 0;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return dao.delete(order) >= 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        return dao.update(order) > 0;
    }

    @Override
    public Order findById(Order order) {
        return dao.findById(order);
    }

    @Override
    public boolean deleteBatch(String[] codes) {
        return dao.deleteBatch(codes) > 0;
    }

    @Override
    public PageInfo<Order> findPage(Order order, PageInfo<Order> page) {
        //处理分页参数
        PageInfo<Order> pageInfo = PageChangeUtils.dealPageInfo(page);
        Page<Object> pageData = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setList(dao.findPage(order));
        page.setPageNum(pageData.getPageNum());
        page.setPageSize(pageData.getPageSize());
        page.setPages(pageData.getPages());
        page.setTotal(pageData.getTotal());
        return page;
    }
}

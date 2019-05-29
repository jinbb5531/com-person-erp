package com.person.erp.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import com.person.erp.order.constant.OrderConstant;
import com.person.erp.order.dao.IOrderDAO;
import com.person.erp.order.dao.IOrderItemDAO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.model.OrderDTO;
import com.person.erp.order.service.IOrderItemService;
import com.person.erp.order.service.IOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
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
    @Resource
    private IOrderItemService orderItemService;

    @Override
    public boolean createOrder(OrderDTO order) {
        User user = TokenUtils.getUser();
        Order order1 = new Order();
        order1.setCreateBy(user.getUserName());
        order1.setCreateAt(new Timestamp(new Date().getTime()));
        order1.setCustomer(order.getCustomer());
        order1.setStatus(OrderConstant.CREATE.getCode());
        //获取当前用户的系统标识符
        order1.setSystemTag(user.getSystemTag());
        order1.setDeadline(order.getDeadline());
        order1.setRemark(order.getRemark());
        //获取生成的订单主键
        String id = order1.getOrderCode();
        List<OrderItem> itemList = order.getItemList();
        //关联订单号
        itemList.forEach(item -> item.setOrderCode(id));
        int insert = dao.insert(order1);
        //      级联添加订单列表
        boolean success = orderItemService.insertBatch(itemList);
        return insert > 0 && success ? true : false;
    }

    @Override
    public boolean deleteOrder(Order order) {
        int delete = dao.delete(order);
        boolean success = orderItemService.deleteByOrderCode(order.getOrderCode());
        return delete > 0 && success ? true : false;
    }

    @Override
    public boolean updateOrder(OrderDTO order) {
        Order order1 = new Order();
        User user = TokenUtils.getUser();
        order1.setCustomer(order.getCustomer());
        order1.setUpdateBy(user.getUserName());
        order1.setUpdateAt(new Timestamp(new Date().getTime()));
        order1.setDeadline(order.getDeadline());
        order1.setOrderCode(order.getOrderCode());
        order1.setRemark(order.getRemark());
        order1.setSystemTag(user.getSystemTag());
        order1.setStatus(order.getStatus());
        order1.setCutter(order.getCutter());
        order1.setHemmer(order.getHemmer());
        order1.setPacker(order.getPacker());
        List<OrderItem> itemList = order.getItemList();
        boolean success = true;
        int update = dao.update(order1);
        if (!itemList.isEmpty()){
            itemList.forEach(item -> item.setOrderCode(order.getOrderCode()));
             success = orderItemService.updateBatch(itemList);
        }
        return update > 0 && success ? true : false;
    }

    @Override
    public Order findById(Order order) {
        return dao.findById(order);
    }

    @Override
    public boolean deleteBatch(String[] codes) {
        int deleteBatch = dao.deleteBatch(codes);
        boolean success = orderItemService.deleteByOrderCodeBatch(codes);
        return deleteBatch > 0 && success ? true : false;
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

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
//        order1.setCreateBy("jin");
        order1.setCreateAt(new Timestamp(new Date().getTime()));
        order1.setCustomer(order.getCustomer());
        order1.setStatus(OrderConstant.CREATE.getCode());
        //获取当前用户的系统标识符
        order1.setSystemTag(user.getSystemTag());
//        order1.setSystemTag(1);
        order1.setDeadline(order.getDeadline());
        order1.setRemark(order.getRemark());
        int insert = dao.insert(order1);
        //获取生成的订单主键
        String id = order1.getOrderCode();
        List<OrderItem> itemList = order.getItemList();
        //关联订单号
        itemList.forEach(item -> item.setOrderCode(id));
        //设置明细的系统标识
        itemList.forEach(item -> item.setSystemTag(user.getSystemTag()));
//        itemList.forEach(item -> item.setSystemTag(1));
        itemList.forEach(item -> item.setPlantNum(
                Math.floor((item.getSizeL() * item.getSizeW() * item.getNumber()) / (item.getProSizeL() * item.getProSizeW())
                )));
        //      级联添加订单列表
        boolean success = orderItemService.insertBatch(itemList);
        return insert > 0 && success ? true : false;
    }

    @Override
    public boolean deleteOrder(Order order) {
        order.setSystemTag(TokenUtils.getUser().getSystemTag());
//        order.setSystemTag(1);
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
//        order1.setUpdateBy("jinbb");
        order1.setUpdateAt(new Timestamp(new Date().getTime()));
        order1.setDeadline(order.getDeadline());
        order1.setOrderCode(order.getOrderCode());
        order1.setRemark(order.getRemark());
        order1.setSystemTag(user.getSystemTag());
//        order1.setSystemTag(1);
//        order1.setStatus(order.getStatus());
//        order1.setCutter(order.getCutter());
//        order1.setHemmer(order.getHemmer());
//        order1.setPacker(order.getPacker());
        List<OrderItem> itemList = order.getItemList();
        boolean success = true;
        int update = dao.update(order1);
        if (!itemList.isEmpty()) {
            itemList.forEach(item -> item.setOrderCode(order.getOrderCode()));
            itemList.forEach(item -> item.setSystemTag(user.getSystemTag()));
//            itemList.forEach(item -> item.setSystemTag(1));
            //跟新计划生产量
            itemList.forEach(item -> item.setPlantNum(
                    Math.floor((item.getSizeL() * item.getSizeW() * item.getNumber()) / (item.getProSizeL() * item.getProSizeW())
                    )));
            success = orderItemService.updateBatch(itemList);
        }
        return update > 0 && success ? true : false;
    }

    @Override
    public boolean updateStatus(Order order) {
        order.setSystemTag(TokenUtils.getUser().getSystemTag());
        return dao.update(order) > 0;
    }

    @Override
    public Order findById(Order order) {
        order.setSystemTag(TokenUtils.getUser().getSystemTag());
        return dao.findById(order);
    }

    @Override
    public boolean deleteBatch(String... codes) {
        Long systemTag = TokenUtils.getUser().getSystemTag();
//        long systemTag =1;
        int deleteBatch = dao.deleteBatch(codes, systemTag);
        boolean success = orderItemService.deleteByOrderCodeBatch(codes, systemTag);
        return deleteBatch >= 0 && success ? true : false;
    }

    @Override
    public PageInfo<Order> findPage(Order order, PageInfo<Order> page) {
        order.setSystemTag(TokenUtils.getUser().getSystemTag());
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

    @Override
    public PageInfo findPageByUser(Order order, PageInfo<Order> page) {
        PageInfo<Order> pageInfo = PageChangeUtils.dealPageInfo(page);
        Page<Object> pageData = PageHelper.startPage(pageInfo.getPageNum(),pageInfo.getPageSize());
        page.setList(dao.findPageByUser(order));
        page.setPageNum(pageData.getPageNum());
        page.setPageSize(pageData.getPageSize());
        page.setPages(pageData.getPages());
        page.setTotal(pageData.getTotal());
        return page;
    }
}

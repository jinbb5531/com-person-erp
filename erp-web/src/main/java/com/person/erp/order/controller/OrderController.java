package com.person.erp.order.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.order.constant.OrderConstant;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.model.OrderDTO;
import com.person.erp.order.service.IOrderItemService;
import com.person.erp.order.service.IOrderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/order")
@Transactional
public class OrderController {

    @Resource
    private IOrderService orderService;
    @Resource
    private IOrderItemService orderItemService;

    /**
     * 新增订单
     *
     * @param order
     * @return result
     */
    @PostMapping
    private HashMap<String, Object> createOrder(@RequestBody OrderDTO order) {

        HashMap<String, Object> result = new HashMap<>();
        Order order1 = new Order();
        order1.setCreateAt(new Timestamp(new Date().getTime()));
        order1.setCustomer(order.getCustomer());
        order1.setStatus(OrderConstant.CREATE.getCode());
        //获取当前用户的系统标识符
        order1.setSystemTag(0);
        order1.setDeadline(order.getDeadline());
        order1.setRemark(order.getRemark());
        boolean success = orderService.createOrder(order1);
        //获取生成的订单主键
//      级联添加订单列表
        if (success) {
            String id = order1.getOrderCode();
            List<OrderItem> itemList = order.getItemList();
            //关联订单号
            itemList.forEach(item->{
                item.setOrderCode(id);
            });
            success = orderItemService.insertBatch(itemList);
        }
        if (success){
            result.put("desc", "新增订单成功！");
        } else {
            result.put("desc", "新增订单失败！");
        }
        return result;
    }

    /**
     * 查询订单
     *
     * @return
     */
    @GetMapping
    private Order get(Order order) {
        return orderService.findById(order);

    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @PutMapping
    private HashMap<String, Object> update(OrderDTO order) {
        HashMap<String, Object> result = new HashMap<>();
        Order order1 = new Order();
        order1.setCustomer(order.getCustomer());
        order1.setUpdateAt(new Timestamp(new Date().getTime()));
        boolean success = orderService.updateOrder(order1);
        if (success) {
            result.put("desc", "修改成功！");
        } else {
            result.put("desc", "修改失败！");
        }
        return result;
    }

    /**
     * 删除订单
     *
     * @param order
     * @return
     */
    @DeleteMapping
    private HashMap<String, Object> delete(Order order) {
        boolean success = orderService.deleteOrder(order);
        HashMap<String, Object> result = new HashMap<String, Object>();
        if (success) {
            result.put("desc", "删除成功！");
        } else {
            result.put("desc", "删除失败！");
        }
        return result;
    }

    /**
     * 批量删除订单
     *
     * @param codes
     * @return
     */
    @DeleteMapping("/batch")
    private HashMap<String, Object> deleteBatch(String codes) {
        boolean success = orderService.deleteBatch(codes.split(","));
        HashMap<String, Object> result = new HashMap<>();
        if (success) {
            result.put("desc", "删除成功！");
        } else {
            result.put("desc", "删除失败！");
        }
        return result;
    }

    /**
     * 条件分页查询
     * @param order
     * @param page
     * @return
     */
    @GetMapping("/page")
    private PageResult<Order> findPage(Order order, PageInfo<Order> page) {
        PageInfo result= orderService.findPage(order, page);
        return PageChangeUtils.pageInfoToPaegResult(result);
    }
}

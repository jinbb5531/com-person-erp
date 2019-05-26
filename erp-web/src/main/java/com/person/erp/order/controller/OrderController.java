package com.person.erp.order.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.order.constant.OrderConstant;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.model.OrderDTO;
import com.person.erp.order.service.IOrderItemService;
import com.person.erp.order.service.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/order")
@Transactional(propagation = Propagation.REQUIRED)
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
    private ResponseEntity createOrder(@Validated @RequestBody OrderDTO order) {

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
//      级联添加订单列表
        if (success) {
            //获取生成的订单主键
            String id = order1.getOrderCode();
            List<OrderItem> itemList = order.getItemList();
            //关联订单号
            itemList.forEach(item->{
                item.setOrderCode(id);
            });
            success = orderItemService.insertBatch(itemList);
            if (success){
                return  ResultUtils.success();
            }else {
                return  ResultUtils.failure("操作失败！");
            }
        } else {
            return ResultUtils.failure("操作失败！");
        }
    }

    /**
     * 查询订单
     *
     * @return
     */
    @GetMapping
    private ResponseEntity get(Order order) {
        return ResultUtils.success(orderService.findById(order));

    }

    /**
     * 修改订单
     *
     * @param order
     * @return
     */
    @PutMapping
    private ResponseEntity update(@Validated @RequestBody OrderDTO order) {
        Order order1 = new Order();
        order1.setCustomer(order.getCustomer());
        order1.setUpdateAt(new Timestamp(new Date().getTime()));
        order1.setDeadline(order.getDeadline());
        order1.setOrderCode(order.getOrderCode());
        order1.setRemark(order.getRemark());
        order1.setSystemTag(0);
        List<OrderItem> itemList = order.getItemList();
        itemList.forEach(item-> item.setOrderCode(order.getOrderCode()));
        boolean success = orderService.updateOrder(order1);
        if (success) {

            success = orderItemService.updateBatch(itemList);
            if(success){
                return ResultUtils.success();
            }else {
                return ResultUtils.failure("操作失败！");
            }
        } else {
            return  ResultUtils.failure("操作失败！");
        }
    }

    /**
     * 删除订单
     *
     * @param orderCode
     * @return
     */
    @DeleteMapping
    private ResponseEntity delete(@RequestParam(name = "orderCode", required = true) String orderCode) {
        Order order = new Order();
        order.setOrderCode(orderCode);
        boolean success = orderService.deleteOrder(order);
        if (success) {
            success = orderItemService.deleteByOrderCode(orderCode);
            if (success){
                return ResultUtils.success();
            }else {
                return  ResultUtils.failure("操作失败！");
            }
        } else {
            return ResultUtils.failure("操作失败！");
        }
    }

    /**
     * 批量删除订单
     *
     * @param codes
     * @return
     */
    @DeleteMapping("/batch")
    private ResponseEntity deleteBatch(@RequestParam(name = "codes",required = true) String codes) {
        String[] params = codes.split(",");
        boolean success = orderService.deleteBatch(params);
        if (success) {
            success = orderItemService.deleteByOrderCodeBatch(params);
            if(success){
                return ResultUtils.success();
            }else {
                return ResultUtils.failure("操作失败！");
            }
        } else {
            return  ResultUtils.failure("操作失败！");
        }
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
        return PageChangeUtils.pageInfoToPageResult(result);
    }
}

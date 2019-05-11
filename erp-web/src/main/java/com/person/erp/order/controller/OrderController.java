package com.person.erp.order.controller;

import com.itexplore.core.api.model.ApiResult;
import com.itexplore.core.api.model.PageResult;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.service.IOrderItemService;
import com.person.erp.order.service.IOrderService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


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
     * @param order
     * @return result
     */
    @PostMapping
    private HashMap<String, Object> createOrder(@RequestBody Order order) {

        HashMap<String, Object> result = new HashMap<String, Object>();

        order.setCreateAt(new Timestamp(new Date().getTime()));
        order.setCreateBy("jinbb");
        order.setStatus(1);
        order.setSystemTag(0);
        order.setDeadline(new Timestamp(System.currentTimeMillis()));
        boolean success = orderService.createOrder(order);
        //获取生成的订单主键
        String id = order.getOrderCode();
        List<OrderItem> itemList = order.getItemList();
        orderItemService.insertBatch(itemList);
//      级联添加订单列表
        if(success){
            result.put("desc","新增成功！");
        }else {
            result.put("desc", "新增订单失败！");
        }
        return result;
    }

    /**
     * 查询订单
     * @return
     */
    @GetMapping
    private Order get(Order order){

        return orderService.findById(order);

    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @PutMapping
    private HashMap<String,Object> update(Order order){
        order.setUpdateAt(new Timestamp(new Date().getTime()));
        orderService.updateOrder(order);
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("id", 211);
        return result;
    }

    /**
     * 删除订单
     * @param order
     * @return
     */
    @DeleteMapping
    private HashMap<String ,Object> delete(Order order){
        boolean success = orderService.deleteOrder(order);
        HashMap<String, Object> result = new HashMap<String, Object>();
        if(success){
            result.put("desc","删除成功！");
        }else{
            result.put("desc","删除失败！");
        }
        return result;
    }

    /**
     * 批量删除订单
     * @param codes
     * @return
     */
    @DeleteMapping("/batch")
    private HashMap<String, Object> deleteBatch(String codes){
        boolean success = orderService.deleteBatch(codes.split(","));
        HashMap<String,Object> result = new HashMap<>();
        if (success){
            result.put("desc","删除成功！");
        }else{
            result.put("desc","删除失败！");
        }
        return result;
    }
}

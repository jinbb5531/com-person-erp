package com.person.erp.order.controller;

import com.person.erp.order.entity.Order;
import com.person.erp.order.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.UUID;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    /**
     * 新增订单
     * @param order
     * @return result
     */
    @PostMapping
    private HashMap<String, Object> createOrder(Order order) {
        order.setOrderCode(UUID.randomUUID().toString());
        order.setCreateAt(new Timestamp(System.currentTimeMillis()));
        order.setCreateBy("jinbb");
        order.setStatus(1);
        order.setSystemTag(0);
        order.setDeadline(new Timestamp(System.currentTimeMillis()));
        orderService.createOrder(order);
        HashMap<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    /**
     * 查询订单
     * @return
     */
    @GetMapping
    private HashMap<String, Object> get(){
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("id", 211);
        return result;

    }

    /**
     * 修改订单
     * @param order
     * @return
     */
    @PutMapping
    private HashMap<String,Object> update(Order order){
        orderService.updateOrder(order);
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("id", 211);
        return result;
    }
}

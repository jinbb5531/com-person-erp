package com.person.erp.order.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import com.person.erp.order.constant.OperateTypeConstant;
import com.person.erp.order.constant.OrderConstant;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderItem;
import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.model.OrderDTO;
import com.person.erp.order.service.IOrderItemService;
import com.person.erp.order.service.IOrderOperateService;
import com.person.erp.order.service.IOrderService;
import com.sun.tools.corba.se.idl.constExpr.Or;
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
public class OrderController {

    @Resource
    private IOrderService orderService;

    @Resource
    private IOrderOperateService operateService;

    /**
     * 新增订单
     *
     * @param order
     * @return result
     */
    @PostMapping
    private ResponseEntity createOrder(@Validated @RequestBody OrderDTO order) {


        boolean success = orderService.createOrder(order);
        if (success) {
            return ResultUtils.success();
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

        boolean success = orderService.updateOrder(order);
        if (success) {
            return ResultUtils.success();
        } else {
            return ResultUtils.failure("操作失败！");
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
            return ResultUtils.success();
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
    private ResponseEntity deleteBatch(@RequestParam(name = "codes", required = true) String codes) {
        String[] params = codes.split(",");
        boolean success = orderService.deleteBatch(params);
        if (success) {
                return ResultUtils.success();
        } else {
            return ResultUtils.failure("操作失败！");
        }
    }

    /**
     * 条件分页查询
     *
     * @param order
     * @param page
     * @return
     */
    @GetMapping("/page")
    private PageResult<Order> findPage(Order order, PageInfo<Order> page) {
        PageInfo result = orderService.findPage(order, page);
        return PageChangeUtils.pageInfoToPageResult(result);
    }

    /**
     * 订单发布
     *
     * @param order
     * @return
     */
    @RequestMapping("/publish")
    private ResponseEntity publish(OrderDTO order) {
        order.setStatus(OrderConstant.PUBLISH.getCode());
        boolean success = orderService.updateOrder(order);
        if (success) {
            return ResultUtils.success();
        } else {
            return ResultUtils.failure("发布失败！");
        }
    }

    /**
     * 工人接单
     *
     * @param order
     * @return
     */
    @PutMapping("/receive")
    private ResponseEntity receiveCut(Order order) {
        if (order.getStatus().equals(OrderConstant.PUBLISH.getCode())) {
//            order.setCutter(TokenUtils.getUser().getUserName());
            order.setCutter("jin");
            order.setStatus(OrderConstant.CUTTING.getCode());
            order.setCutAt(new Timestamp(new Date().getTime()));
            order.setUpdateAt(new Timestamp(new Date().getTime()));
            boolean success = orderService.updateStatus(order);
            if (success) {
                return ResultUtils.success();
            } else {
                return ResultUtils.failure("接单失败！");
            }
        } else if (order.getStatus().equals(OrderConstant.CUT_END.getCode())) {
            order.setHemmer(TokenUtils.getUser().getUserName());
            order.setStatus(OrderConstant.HEMMING.getCode());
            order.setHemAt(new Timestamp(new Date().getTime()));
            order.setUpdateAt(new Timestamp(new Date().getTime()));
            boolean success = orderService.updateStatus(order);
            if (success) {
                return ResultUtils.success();
            } else {
                return ResultUtils.failure("接单失败！");
            }
        } else if (order.getStatus().equals(OrderConstant.HEM_END.getCode())) {
            order.setPacker(TokenUtils.getUser().getUserName());
            order.setStatus(OrderConstant.PACKING.getCode());
            order.setPackAt(new Timestamp(new Date().getTime()));
            order.setUpdateAt(new Timestamp(new Date().getTime()));
            boolean success = orderService.updateStatus(order);
            if (success) {
                return ResultUtils.success();
            } else {
                return ResultUtils.failure("接单失败！");
            }
        } else {
            return ResultUtils.failure("订单状态有误，不可接单！");
        }
    }

    /**
     * 裁剪提交
     *
     * @param orderOperate
     * @return
     */
    @PutMapping("/submit/cut")
    private ResponseEntity submitCut(OrderOperate orderOperate) {
        orderOperate.setType(OperateTypeConstant.CUT.getType());
        orderOperate.setOperator(TokenUtils.getUser().getUserName());
        orderOperate.setOperaTime(new Timestamp(new Date().getTime()));
        OrderDTO order = new OrderDTO();
        order.setStatus(OrderConstant.CUT_END.getCode());
        boolean success = operateService.insert(orderOperate, order);
        if (success) {
                return ResultUtils.success();
        } else {
            return ResultUtils.failure("提交失败！");
        }
    }

    /**
     * 缝边提交
     *
     * @param orderOperate
     * @return
     */
    @PutMapping("/submit/hem")
    private ResponseEntity submitHem(OrderOperate orderOperate) {
        orderOperate.setType(OperateTypeConstant.HEM.getType());
        orderOperate.setOperator(TokenUtils.getUser().getUserName());
        orderOperate.setOperaTime(new Timestamp(new Date().getTime()));
        OrderDTO order = new OrderDTO();
        order.setStatus(OrderConstant.HEM_END.getCode());
        boolean success = operateService.insert(orderOperate, order);
        if (success) {
                return ResultUtils.success();
        } else {
            return ResultUtils.failure("提交失败！");
        }
    }

    /**
     * 包装提交
     *
     * @param orderOperate
     * @return
     */
    @PutMapping("/submit/pack")
    private ResponseEntity submitPack(OrderOperate orderOperate) {
        orderOperate.setType(OperateTypeConstant.PACK.getType());
        orderOperate.setOperator(TokenUtils.getUser().getUserName());
        orderOperate.setOperaTime(new Timestamp(new Date().getTime()));
        OrderDTO order = new OrderDTO();
        order.setPacker(TokenUtils.getUser().getUserName());
        order.setStatus(OrderConstant.PACK_END.getCode());
        boolean success = operateService.insert(orderOperate, order);
        if (success) {
                return ResultUtils.success();
        } else {
            return ResultUtils.failure("提交失败！");
        }
    }
}

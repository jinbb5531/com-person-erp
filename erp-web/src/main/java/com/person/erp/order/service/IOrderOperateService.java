package com.person.erp.order.service;

import com.person.erp.identity.model.UserDTO;
import com.person.erp.order.entity.Order;
import com.person.erp.order.entity.OrderOperate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IOrderOperateService {
    /**
     * 订单操作表
     * @param orderOperate
     * @return
     */
    boolean insert(OrderOperate orderOperate, Order order);

    /**
     * 获取裁剪数量
     * @param orderCode
     * @return
     */
    OrderOperate getNum(String orderCode, long syetemTag);

    /**
     * 订单操作表
     * @param orderOperate
     * @return
     */
    boolean insert(OrderOperate orderOperate);

    /**
     * 统计出对应用户的计件数和
     * @param dtoList
     * @param startTime
     * @param endTime
     * @return
     */
    List<OrderOperate> sumCutNumGroupByUserList(List<UserDTO> dtoList, Date startTime, Date endTime);
}

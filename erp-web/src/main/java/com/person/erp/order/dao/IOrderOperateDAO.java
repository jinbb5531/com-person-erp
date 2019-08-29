package com.person.erp.order.dao;

import com.person.erp.identity.model.UserDTO;
import com.person.erp.order.entity.OrderOperate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * 订单操作
 */
@Mapper
public interface IOrderOperateDAO {

    /**
     * 插入订单操作数据
     * @param orderOperate
     * @return
     */
    int insert(OrderOperate orderOperate);

    /**
     * 获取订单的裁剪数
     */
    OrderOperate getCutNum(@Param("orderCode") String orderCode, @Param("systemTag") long systemTag);
    /**
     * 获取实际产量
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 期间的每条对象
     */
    List<OrderOperate> realYiely(@Param(value = "startDate") Long startDate, @Param(value = "endDate") Long endDate, @Param(value = "systemTag") Long systemTag);

    /**
     * 统计出对应用户的计件数和
     * @param dtoList
     * @param startTime
     * @param endTime
     * @return
     */
    List<OrderOperate> sumCutNumGroupByUserList(@Param(value = "dtoList") List<UserDTO> dtoList,
                                                @Param(value = "startDate") Date startTime,
                                                @Param(value = "endDate")Date endTime);
}

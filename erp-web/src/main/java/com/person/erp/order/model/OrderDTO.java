package com.person.erp.order.model;

import com.person.erp.order.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单交互模型
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO implements Serializable {

    /**
     * 订单主键
     */

    private String orderCode;
    /**
     * 客户名
     */
    @NotBlank(message = "【customer】 不能为空！")
    private String customer;

    /**
     * 创建人
     */
    @NotBlank(message = "【createBy】 不能为空！")
    private String createBy;

    /**
     * 裁剪人
     */
    private String cutter;
    /**
     * 缝边人
     */
    private String hemmer;
    /**
     * 包装者
     */
    private String packer;
    /**
     * 截止时间
     */
    private Timestamp deadline;

    /**
     * 备注
     */
    private String remark;
    /**
     * 订单明细，一对多
     */
    @NotEmpty(message = "【itemList】 不能为空！")
    private List<OrderItem> itemList;


}




package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 订单明细
 */
@Getter
@Setter
@Table(name = "erp_order_item")
public class OrderItem {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 订单号
     */
    @NotEmpty(message = "【orderCode】不能为空！")
    private String orderCode;
    /**
     * 品名
     */
    @NotEmpty(message = "【name】不能为空！")
    private String name;
    /**
     * 颜色
     */
    private String color;
    /**
     * 原料长
     */
    @Min(value = 0)
    private Double sizeL;
    /**
     * 原料宽
     */
    @Min(value = 0)
    private Double sizeW;
    /**
     * 裁剪的长
     */
    @Min(value = 0)
    private Double proSizeL;
    /**
     * 裁剪的宽
     */
    @Min(value = 0)
    private Double proSizeW;
    /**
     * 原料数量
     */
    @Min(value = 0)
    private Integer number;
    /**
     * 计划生产量
     */
    @Min(value = 0)
    private Integer plantNum;
    /**
     * 系统标识
     */
    private long systemTag;

}

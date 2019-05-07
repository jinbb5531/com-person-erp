package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 订单明细
 */
@Getter
@Setter
public class OrderItem {
    /**
     * 主键
     */
    private Long code;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 品名
     */
    private String name;
    /**
     * 颜色
     */
    private String color;
    /**
     * 原料长
     */
    private Double SizeL;
    /**
     * 原料宽
     */
    private Double SizeW;
    /**
     * 裁剪的长
     */
    private Double proSizeL;
    /**
     * 裁剪的宽
     */
    private Double proSizeW;
    /**
     * 原料数量
     */
    private Integer number;
    /**
     * 计划生产量
     */
    private Integer plantNum;
    /**
     * 系统标识
     */
    private Integer systemTag;
}

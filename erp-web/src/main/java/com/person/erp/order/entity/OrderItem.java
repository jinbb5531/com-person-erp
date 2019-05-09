package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private long code;
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
    private Double sizeL;
    /**
     * 原料宽
     */
    private Double sizeW;
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
    private long systemTag;
}
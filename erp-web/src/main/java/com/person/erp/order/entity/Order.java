package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单表
 */
@Setter
@Getter
public class Order {

    /**
     * 订单主键
     */
    private String orderCode;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 客户名
     */
    private String customer;
    /**
     * 创建时间
     */
    private Timestamp createAt;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Timestamp updateAt;
    /**
     * 订单状态
     */
    private Integer status;
    /**
     * 裁剪人
     */
    private String cutter;
    /**
     * 裁剪时间
     */
    private Timestamp cutAt;
    /**
     * 缝边人
     */
    private String hemmer;
    /**
     * 缝边时间
     */
    private Timestamp hemAt;
    /**
     * 包装者
     */
    private String packer;
    /**
     * 包装时间
     */
    private Timestamp packAt;
    /**
     * 截止时间
     */
    private Timestamp deadline;
    /**
     * 系统标识
     */
    private long systemTag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 订单明细，一对多
     */
    private List<OrderItem> itemList;
    /**
     * 订单操作，一对一
     */
    private OrderOperate operate;

    /**
     * 图片
     */
    private String image;

    /**
     * 原料数量
     */
    private Integer number;

    /**
     * 订单金额
     */
    private Double cost;
    /**
     * 封装关键词
     */
    private String keyword;
}

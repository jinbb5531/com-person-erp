package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 * 订单表
 */
@Setter
@Getter
@Table(name = "erp_order")
public class Order {

    /**
     * 订单主键
     */
    @Id
    private String orderCode;
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

}
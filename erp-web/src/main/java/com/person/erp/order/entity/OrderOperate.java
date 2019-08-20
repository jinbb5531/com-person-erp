package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 订单操作表
 */
@Setter
@Getter
public class OrderOperate {

    /**
     * 主键
     */
    private Long code;
    /**
     * 订单号
     */
    private String orderCode;
    /**
     * 操作人
     */
    private String operator;
    /**
     * 操作类型
     */
    private Integer type;
    /**
     * 裁剪数量
     */
    private Integer cutNum;
    /**
     * 裁剪后的重量
     */
    private Double weight;
    /**
     * 操作时间
     */
    private Timestamp operaTime;
    /**
     * 系统标识
     */
    private long systemTag;

    /**
     * 参与者
     */
    private String coagent;

    /**
     * 参与人数
     */
    private Integer peopleCount;

    /**
     * 操作人userCode
     */
    private String operaCode;
}

package com.person.erp.order.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 订单，操作，用户查询利益关系表
 */
@Setter
@Getter
public class RelationVO {
    private Double cost;
    private String customer;
    private Double wages;
    private Timestamp createAt;
}

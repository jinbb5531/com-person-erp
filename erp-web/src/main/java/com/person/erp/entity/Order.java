package com.person.erp.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {

    /**
     * 订单主键
     */
    private String code;
    /**
     * 客户名
     */
    private String guestName;
}

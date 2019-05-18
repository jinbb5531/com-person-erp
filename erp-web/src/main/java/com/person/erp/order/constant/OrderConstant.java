package com.person.erp.order.constant;

import lombok.Getter;

/**
 * 订单状态常量
 */
@Getter
public enum OrderConstant {

    CREATE(0,"订单创建"),
    PUBLISH(1,"订单发布"),
    CUTTING(2,"裁剪中"),
    CUT_END(3,"裁剪完成"),
    HEMMING(4,"缝边中"),
    HEM_END(5,"缝边完成"),
    PACKING(6,"包装中"),
    PACK_END(7,"包装完成");

    private Integer code;
    private String desc;

    OrderConstant(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}

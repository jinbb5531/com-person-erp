package com.person.erp.order.constant;

import lombok.Getter;

/**
 * 操作类型常量
 */
@Getter
public enum OperateTypeConstant {

    CUT(0,"裁剪"),
    HEM(1,"缝边"),
    PACK(2,"包装");

    private Integer type;
    private String desc;

    OperateTypeConstant(Integer type, String desc){
        this.desc = desc;
        this.type = type;
    }

}

package com.person.erp.qrcode.constants;

import lombok.Getter;

/**
 * 二维码常量
 */
@Getter
public enum QrConstants {
    BLACK(-0x1000000,"黑色"),
    WHITE(-0x1, "白色");
    private Integer type;
    private String desc;

    QrConstants(Integer type, String desc){
        this.desc = desc;
        this.type = type;
    }
}

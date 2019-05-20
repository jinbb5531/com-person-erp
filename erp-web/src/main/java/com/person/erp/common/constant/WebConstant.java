package com.person.erp.common.constant;

/**
 * 常量类<br>
 * @description 系统中常用到的一些公共常量。
 * @author zhuwj
 * @since 2017年10月20日
 */
public class WebConstant {

    /**
     * 验证码的标识
     */
    public final static String VERIFY_CODE = "VERIFY_CODE";

    /**
     * 权限标识
     */
    public final static String PERMISSION = "PERMISSION";

    /**
     * 记住用户的标识
     */
    public final static int REMEMBER_USER = 1;

    /**
     * Cookie名称
     */
    public final static String COOKIE_NAME = "ERP_WEB_COOKIE";

    /**
     * cookie最大时长
     */
    public final static int MAX_COOKIE = 60 * 60 * 24 * 7;

    /**
     * 二维码对象大小
     */
    public final static int QRCODE_SIZE = 160;

    /**
     * 是否显示的枚举
     */
    public enum ShowFlag {
        SHOW(0),

        HIDE(1);
        private int value;
        ShowFlag(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 超级管理的系统标识的值
     */
    public final static long SUPER_MANAGER_TAG = 0;

    public enum Sex {
        WOMAN(0),

        MAN(1);
        private int value;
        Sex(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}

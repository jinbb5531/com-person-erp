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

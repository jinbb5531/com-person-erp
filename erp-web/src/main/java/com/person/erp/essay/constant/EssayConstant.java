package com.person.erp.essay.constant;

import lombok.Getter;

/**
 * <p>EssayConstant.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:40
 */
public class EssayConstant {

    public static final Integer TOP = 1;

    public static final Integer NORMAL = 10;

    /**
     * 0：草稿；1：上架；2：下架；
     */
    @Getter
    public enum Status {

        DRAFT(0),
        PUBLISH(1),
        DOWN(2);

        private int value;

        Status(int value) {
            this.value = value;
        }
    }

}

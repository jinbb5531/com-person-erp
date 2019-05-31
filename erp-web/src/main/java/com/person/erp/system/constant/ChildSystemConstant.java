package com.person.erp.system.constant;

import lombok.Getter;

/**
 * <p>ChildSystemConstant.java</p>
 *
 * @author zhuwj
 * @since 2019/5/31 17:07
 */
public class ChildSystemConstant {

    @Getter
    public enum Status {
        USE(0),
        BAN(1);
        private int value;
        Status(int value) {
            this.value = value;
        }
    }

}

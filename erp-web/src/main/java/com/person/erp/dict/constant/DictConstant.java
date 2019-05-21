package com.person.erp.dict.constant;

/**
 * <p>DictConstant.java</p>
 *
 * @author zhuwj
 * @since 2019/5/21 9:26
 */
public class DictConstant {

    public enum Status {
        USE(0),
        BAN(1);
        private Integer value;
        Status(Integer value) {
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }
}

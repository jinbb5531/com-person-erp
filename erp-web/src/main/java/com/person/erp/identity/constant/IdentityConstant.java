package com.person.erp.identity.constant;

/**
 * <p>IdentityConstant.java</p>
 *
 * @author zhuwj
 * @since 2019/5/22 11:52
 */
public class IdentityConstant {

    public enum ModuleFlag {

        MODULE(0),
        OPERATE(1);

        private Integer value;

        ModuleFlag(int value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    public enum UseFlag {
        USE(0),
        BAN(1);
        private Integer value;
        UseFlag(Integer value) {
            this.value = value;
        }
        public Integer getValue() {
            return value;
        }
    }
}

package com.person.erp.identity.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>UserRole.java</p>
 *
 * @author zhuwj
 * @since 2019/5/6 18:33
 */
@Getter
@Setter
public class UserRole {

    /**
     * 系统标识
     */
    private long systemTag;

    /**
     * 用户主键
     */
    private String userCode;

    /**
     * 角色主键
     */
    private long roleCode;

}

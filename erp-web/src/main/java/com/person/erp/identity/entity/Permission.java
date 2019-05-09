package com.person.erp.identity.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author zhuwj
 * @description 权限实体
 * @since 2018/4/20
 */
@Getter
@Setter
public class Permission {

    /**
     * 角色主键
     */
    private long roleId;

    /**
     * 菜单主键
     */
    private long menuId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Timestamp createAt;

}

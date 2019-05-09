package com.person.erp.identity.model;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * <p>RoleDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/8 13:01
 */
public class RoleDTO {
    /**
     * 角色主键
     */
    @NotEmpty(message = "角色主键不能为空！")
    private String roleCode;

    /**
     * 角色名
     */
    @NotEmpty(message = "角色名不能为空！")
    private String roleName;

    /**
     * 显示标识（超级管理员设置为不显示）； 0：显示；1：不显示
     */
    private Integer showFlag;
}

package com.person.erp.identity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Transient;
import java.sql.Timestamp;

/**
 * @author zhuwj
 * @description 角色
 * @since 2018/4/20
 */
@Getter
@Setter
public class Role {

    /**
     * 角色主键
     */
    private long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 显示标识（超级管理员设置为不显示）； 0：显示；1：不显示
     */
    private int showFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Timestamp createAt;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 修改时间
     */
    private Timestamp updateAt;

    /**
     * 系统标识
     */
    private long systemTag;

    @Transient
    private long[] ids;
}

package com.person.erp.identity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author zhuwj
 * @description 角色
 * @since 2018/4/20
 */
@Getter
@Setter
@Table(name = "ERP_ROLE")
public class Role implements Serializable {

    /**
     * 角色主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 显示标识（超级管理员设置为不显示）； 0：显示；1：不显示
     */
    private Integer showFlag;

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
    private Long systemTag;

    @Transient
    private Long[] ids;

    /**
     * 角色所拥有的菜单
     */
    @Transient
    private List<Menu> menuList;
}

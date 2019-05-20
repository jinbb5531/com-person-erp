package com.person.erp.identity.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author zhuwj
 * @description 菜单实体
 * @since 2018/4/18
 */
@Getter
@Setter
public class Menu {

    /**
     * 菜单主键
     */
    private Long id;

    /**
     * 上级菜单主键
     */
    private Long parentId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 模块标识
     */
    private Integer moduleFlag;

    /**
     * 菜单URL
     */
    private String menuUrl;

    /**
     * 图标路径
     */
    private String iconPath;

    /**
     * 排序
     */
    private Integer queueNum;

    /**
     * 是否启用
     */
    private Integer useFlag;

    /**
     * 是否第三方
     */
    private Integer outsideFlag;

    /**
     * 窗口模式
     */
    private Integer windowModel;

    /**
     * 备注
     */
    private String remark;

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
     * 显示标识 0：显示；1：不显示
     */
    private Integer showFlag;

}

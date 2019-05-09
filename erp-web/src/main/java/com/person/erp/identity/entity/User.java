package com.person.erp.identity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * <p>User.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * 用户帐号
     */
    private String userCode;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 电话号
     */
    private String telPhone;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 邮箱
     */
    private String userMail;

    /**
     * Q号
     */
    private String qqNum;

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

    /**
     * 基本工资
     */
    private String baseSalary;

    /**
     * 入职时间
     */
    private Timestamp joinAt;

    /**
     * 性别；0:女   1：男
     */
    private Integer sex;

    /**
     * 工种
     */
    private String workKind;

    /**
     * 对应的所有角色
     */
    private List<Role> roleList;

}

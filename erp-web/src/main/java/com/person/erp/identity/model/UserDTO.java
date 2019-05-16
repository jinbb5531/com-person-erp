package com.person.erp.identity.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * <p>UserDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:16
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class
UserDTO implements Serializable {
    /**
     * 用户帐号
     */
    @NotEmpty(message = "用户帐号不能为空！")
    @Pattern(regexp = "[a-zA-Z0-9_]{4,10}", message = "用户帐号必须由4-10位数字、字母或下划线组成")
    private String userCode;

    /**
     * 用户密码
     */
    @NotEmpty(message = "密码不能为空！")
    private String userPwd;

    /**
     * 用户姓名
     */
    @NotEmpty(message = "用户姓名不能为空！")
    private String userName;

    /**
     * 状态（性质，0：临时工  1：全日制）
     */
    @NotNull(message = "状态不能为空！")
    private Integer status;

    /**
     * 电话号
     */
    private String telPhone;

    /**
     * 手机号（需要校验唯一性）
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
    private String joinDate;

    /**
     * 性别；0:女   1：男
     */
    private Integer sex;

    /**
     * 工种
     */
    private String workKind;

    /**
     * 角色主键集
     */
    private long[] roleIds;

}

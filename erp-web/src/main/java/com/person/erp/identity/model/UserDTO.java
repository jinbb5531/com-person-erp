package com.person.erp.identity.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Insert;
import com.person.erp.common.valid.Update;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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
public class UserDTO {
    /**
     * 用户帐号
     */
    @NotEmpty(groups = {Delete.class, Default.class}, message = "[userCode] 用户帐号不能为空！")
    @Pattern(groups = {Delete.class, Default.class}, regexp = "[a-zA-Z0-9_]{4,10}", message = "[userCode] 用户帐号必须由4-10位数字、字母或下划线组成")
    private String userCode;

    /**
     * 用户密码
     */
    @NotEmpty(groups = {Insert.class}, message = "[userPwd] 密码不能为空！")
    private String userPwd;

    /**
     * 用户姓名
     */
    @NotEmpty(message = "[userName] 用户姓名不能为空！")
    private String userName;

    /**
     * 状态（性质，0：临时工  1：全日制）
     */
    @NotNull(message = "[status] 性质不能为空！")
    @Min(value = 0, message = "[status] 性质最小值为0")
    @Max(value = 1, message = "[status] 性质最大值为1")
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
    @NotNull(groups = {Update.class, Delete.class}, message = "[systemTag] 系统标识不能为空")
    @Min(groups = {Update.class, Delete.class}, value = 0, message = "[systemTag] 系统标识最小值为 0")
    private Long systemTag;

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
     * 地址
     */
    private String userAddr;

    /**
     * 角色主键集
     */
    private Long[] roleIds;

    /**
     * 角色集
     */
    private List<RoleDTO> roles;

    /**
     * 单件工资
     */
    private Double unitCost;

    /**
     * 当前总数
     */
    private Integer countNum;

    /**
     * 总工资
     */
    private Double sumSalary;

    /**
     * 开始时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 计算出总工资数（基本工资 + 单件工资*当月总件数）
     * @return 基本工资 + 单件工资*当月总件数
     */
    public Double getSumSalary() {

        BigDecimal nimbleSalary = BigDecimal.valueOf(0);
        if (countNum != null && unitCost != null) {
            BigDecimal countNumDecimal = BigDecimal.valueOf(countNum);
            BigDecimal unitCostDecimal = BigDecimal.valueOf(unitCost);
            nimbleSalary = countNumDecimal.multiply(unitCostDecimal);
        }

        BigDecimal baseSalaryDecimal = BigDecimal.valueOf(0);
        if (baseSalary != null && !StringUtils.isEmpty(baseSalary)) {
            baseSalaryDecimal = BigDecimal.valueOf(Double.valueOf(baseSalary));
        }
        return baseSalaryDecimal.add(nimbleSalary).doubleValue();
    }

}

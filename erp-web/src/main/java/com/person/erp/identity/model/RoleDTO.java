package com.person.erp.identity.model;

import com.person.erp.common.valid.Update;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * <p>RoleDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/8 13:01
 */
@Getter
@Setter
public class RoleDTO {
    /**
     * 角色主键
     */
    @NotNull(groups = { Update.class },message = "[id] 角色主键不能为空！")
    @Min(groups = {Update.class}, message = "[id] 不能小于1", value = 1L)
    private Long id;

    /**
     * 角色名
     */
    @NotEmpty(message = "[roleName] 角色名不能为空！")
    private String roleName;

    /**
     * 显示标识（超级管理员设置为不显示）； 0：显示；1：不显示
     */
    private Integer showFlag;

    /**
     * 菜单主键数组
     */
    private Long[] menuIds;

    /**
     * 系统标识
     */
    private Long systemTag;
}

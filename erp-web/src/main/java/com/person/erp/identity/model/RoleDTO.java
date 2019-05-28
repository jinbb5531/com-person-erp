package com.person.erp.identity.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

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

    /**
     * 所拥有的菜单集
     */
    private List<MenuDTO> menus;

    /**
     * 创建时间
     */
    private Timestamp createAt;

    @NotEmpty(groups = {Delete.class}, message = "[ids] 角色主键集不能为空")
    private Long[] ids;
}

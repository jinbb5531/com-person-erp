package com.person.erp.identity.model;

import com.person.erp.identity.entity.Menu;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * <p>MenuDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/8 13:33
 */
@Getter
@Setter
public class MenuDTO {
    /**
     * 菜单主键集
     */
    @NotNull(message = "菜单主键集不能为空！")
    private String[] ids;

    /**
     * 菜单主键
     */
    @NotEmpty(message = "菜单主键不能为空！")
    private Long id;

    /**
     * 上级菜单主键
     */
    private Long parentId;

    /**
     * 菜单名
     */
    @NotEmpty(message = "菜单名不能为空！")
    private String menuName;

    /**
     * 模块标识
     */
    private Integer moduleFlag;

    /**
     * 菜单URL
     */
    @NotEmpty(message = "菜单URL不能为空！")
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
     * 父菜单实体
     */
    private Menu parentMenu;

    /**
     * 子菜单
     */
    private List<MenuDTO> childMenus;
}

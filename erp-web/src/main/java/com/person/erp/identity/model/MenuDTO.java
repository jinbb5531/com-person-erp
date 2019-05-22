package com.person.erp.identity.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.identity.entity.Menu;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
public class
MenuDTO {

    /**
     * 菜单主键
     */
    @NotNull(groups = {Update.class, Delete.class}, message = "[id] 菜单主键不能为空！")
    private Long id;

    /**
     * 上级菜单主键
     */
    @Min(value = 1, message = "[parentId] 父主键最小值为1")
    private Long parentId;

    /**
     * 菜单名
     */
    @NotEmpty(message = "[menuName] 菜单名不能为空！")
    private String menuName;

    /**
     * 模块标识
     */
    @Min(value = 0, message = "[moduleFlag] 模块标识最小值为0")
    @Max(value = 1, message = "[moduleFlag] 模块标识最大值为1")
    private Integer moduleFlag;

    /**
     * 菜单URL
     */
    @NotEmpty(message = "[menuUrl] 菜单URL不能为空！")
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
     * 显示标识
     */
    private Integer showFlag;

    /**
     * 父菜单名
     */
    private String parentName;

    /**
     * 子菜单
     */
    private List<MenuDTO> childMenus;
}

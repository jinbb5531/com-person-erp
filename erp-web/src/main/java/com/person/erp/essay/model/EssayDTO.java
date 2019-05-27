package com.person.erp.essay.model;

import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>EssayDTO.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 18:53
 */
@Data
public class EssayDTO implements Serializable {

    /**
     * 文章主键
     */
    @NotNull(groups = {Update.class}, message = "[id] 文章主键不能为空！")
    private Long id;

    /**
     * 文章类型
     */
    private Integer essayType;

    /**
     * 主标题
     */
    @NotBlank(message = "[mainTitle] 标题不能为空！")
    private String mainTitle;

    /**
     * 副标题
     */
    private String secondaryTitle;

    /**
     * 标题图片路径
     */
    @NotBlank(message = "[imgPath] 文章封面不能为空！")
    private String imgPath;

    /**
     * 文章摘要
     */
    @NotBlank(message = "[summary] 文章摘要不能为空！")
    private String summary;

    /**
     * 文章来源
     */
    private String essaySource;

    /**
     * 文章排序
     */
    @Min(value = 1, message = "[queueNum] 排序为整数且不能小于1")
    private Integer queueNum;

    /**
     * 作者
     */
    @NotBlank(message = "[author] 作者不能为空")
    private String author;

    /**
     * 发布（上架）时间
     */
    private Timestamp publishDate;

    /**
     * 下架时间
     */
    private Timestamp downDate;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 自动发布标识；1：自动；2：手动; 默认为2
     */
    private Integer autoFlag;

    /**
     * 文章内容
     */
    @NotBlank(message = "[content] 文章内容不能为空")
    private String content;

    @NotEmpty(groups = {Delete.class}, message = "[ids] 文章主键集不能为空")
    private Long[] ids;

}

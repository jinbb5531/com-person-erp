package com.person.erp.essay.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <p>Essay.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 18:48
 */
@Getter
@Setter
@Table(name = "ERP_ESSAY")
public class Essay {

    @Transient
    private Long[] ids;

    /**
     * 文章主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 文章类型
     */
    private Integer essayType;

    /**
     * 主标题
     */
    private String mainTitle;

    /**
     * 副标题
     */
    private String secondaryTitle;

    /**
     * 标题图片路径
     */
    private String imgPath;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章来源
     */
    private String essaySource;

    /**
     * 文章排序
     */
    private Integer queueNum;

    /**
     * 作者
     */
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

    private String createBy;

    private Timestamp createAt;

    private String updateBy;

    private Timestamp updateAt;

}

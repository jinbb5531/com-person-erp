package com.person.erp.dict.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * <p>Dict.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:10
 */
@Data
@Table(name = "ERP_DICT")
public class Dict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dictName;

    private Integer status;

    private String iconPath;

    private String createBy;

    private Timestamp createAt;

    private String updateBy;

    private Timestamp updateAt;

    private Long typeId;

    @Transient
    private DictType dictType;

    @Transient
    private Long[] ids;

}

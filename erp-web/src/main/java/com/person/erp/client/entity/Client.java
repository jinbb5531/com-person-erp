package com.person.erp.client.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 客户管理
 */
@Setter
@Getter
@Table(name = "ERP_CLIENT")
public class Client {

    /*主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /*
        客户名
     */
    @NotEmpty(message = "【clientName】 不能为空！")
    private String clientName;

    /*
        客户地址
     */
    private String clientAddr;

    /*
        客户电话
     */
    @NotEmpty(message = "【clientName】 不能为空！")
    private String clientPhone;

    /*
        应付
     */
    @NotNull(message = "【payable】 不能为空！")
    private BigDecimal payable;

    /*
        实付
     */
    @NotNull(message = "【payable】 不能为空！")
    private BigDecimal paid;

    /*
        是否结清
     */
    private int isSettle;

    /*
        系统标识
     */
    private long systemTag;

    /*
        创建人
     */
    private String createBy;

    /*
        创建时间
     */
    private Timestamp createAt;

    /*
        更新人
     */
    private String updateBy;

    /*
        更新时间
     */
    private Timestamp updateAt;
}

package com.person.erp.trade.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>ProvideTrade.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:30
 */
@Data
@Table(name = "ERP_PROVIDE_TRADE")
public class ProvideTrade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contactPhone;

    private String contactAddress;

    private String mainBusiness;

    private String remark;

    private Long systemTag;

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

}

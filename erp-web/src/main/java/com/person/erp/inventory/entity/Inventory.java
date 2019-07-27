package com.person.erp.inventory.entity;

import com.person.erp.common.valid.Single;
import com.person.erp.common.valid.Update;
import com.person.erp.common.valid.UpdatePwd;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.sql.Timestamp;

/**
 * <p>Inventory.java</p>
 *
 * @author zhuwj
 * @since 2019/7/22 22:56
 */
@Data
@Table(name="ERP_INVENTORY")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Single.class, Update.class, UpdatePwd.class}, message = "[id] 主键不能为空")
    private Long id;

    @NotBlank(groups = {Default.class, Update.class}, message = "[name] 名称不能为空")
    private String name;

    private String standard;

    private String colour;

    private String weight;

    @NotBlank(groups = Single.class, message = "[id] 主键不能为空")
    private String amount;

    private String imaPath;

    private Timestamp reachAt;

    private Timestamp deliveryAt;

    /**
     * 辅料是否到位。0：是，1：否
     */
    private Integer status;

    /**
     * 交货状态，0：未交货；1：已交货
     */
    @NotNull(groups = UpdatePwd.class, message = "[deliveryStatus] 不能为空")
    private Integer deliveryStatus;

    private Timestamp createAt;

    private String createBy;

    private String updateBy;

    private Timestamp updateAt;

    private Long systemTag;
}

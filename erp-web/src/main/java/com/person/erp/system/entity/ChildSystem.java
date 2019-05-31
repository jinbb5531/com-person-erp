package com.person.erp.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>ChildSystem.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:15
 */
@Getter
@Setter
@Table(name = "ERP_SYSTEM")
public class ChildSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer status;

}

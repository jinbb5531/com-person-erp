package com.person.erp.essay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Content.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:07
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ERP_CONTENT")
public class Content {

    @Id
    private Long essayId;

    private String mainContent;

}

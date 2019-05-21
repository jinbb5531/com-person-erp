package com.person.erp.dict.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>entity.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:08
 */
@Data
@NoArgsConstructor
@Table(name = "ERP_TYPE")
public class
DictType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "[typeName] 类型名不能为空")
    private String typeName;

    public DictType(String typeName) {
        this.typeName = typeName;
    }

}

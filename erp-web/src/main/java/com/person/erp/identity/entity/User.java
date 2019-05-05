package com.person.erp.identity.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>User.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:13
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "erp_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}

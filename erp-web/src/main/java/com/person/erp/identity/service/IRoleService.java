package com.person.erp.identity.service;

import com.person.erp.identity.entity.Role;
import com.person.erp.identity.model.RoleDTO;

import java.util.List;

/**
 * <p>IRoleService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/9 17:09
 */
public interface IRoleService {
    /**
     * 按条件获取角色集
     * @author zhuwj
     * @since 2019/5/9 17:24
     * @param role
     * @return java.util.List<com.person.erp.identity.entity.Role>
     */
    List<Role> findList(Role role);

    /**
     * 新增角色
     * @author zhuwj
     * @since 2019/5/17 13:23
     * @param roleDTO
     * @return long
     */
    long addRole(RoleDTO roleDTO);
}

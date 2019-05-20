package com.person.erp.identity.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
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

    /**
     * 批量添加角色权限
     * @author zhuwj
     * @since 2019/5/18 10:55
     * @param menuIds
     * @param roleId
     * @param createBy
     * @return boolean
     */
    boolean insertPermissionBatch(Long[] menuIds, Long roleId, String createBy);

    /**
     * 修改角色
     * @author zhuwj
     * @since 2019/5/18 10:26
     * @param roleDTO
     * @return boolean
     */
    boolean updateRole(RoleDTO roleDTO);

    /**
     * 通过主键查找角色，并封装其所有菜单权限
     * @author zhuwj
     * @since 2019/5/18 17:03
     * @param id
     * @return com.person.erp.identity.model.RoleDTO
     */
    RoleDTO getRoleById(Long id);

    /**
     * 找出所有角色集，非超级管理员只返回“显示”的角色
     * @author zhuwj
     * @since 2019/5/19 8:51
     * @return java.util.List<com.person.erp.identity.model.RoleDTO>
     */
    List<RoleDTO> findAll();

    /**
     * 按名称模糊分页查询
     * @author zhuwj
     * @since 2019/5/19 9:53
     * @param roleName
     * @param pager
     * @return com.github.pagehelper.PageInfo<com.person.erp.identity.entity.Role>
     */
    PageInfo<Role> findPage(String roleName, Pager pager);

    /**
     * 批量删除角色
     * @author zhuwj
     * @since 2019/5/20 8:13
     * @param ids
     * @return boolean
     */
    boolean deleteBatch(Long[] ids);
}

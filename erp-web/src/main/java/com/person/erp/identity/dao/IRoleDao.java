package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.person.erp.identity.entity.Permission;
import com.person.erp.identity.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuwj
 * @description
 * @since 2018/4/20
 */
@MyMapper
public interface IRoleDao {
    
    /**
     * 新增角色
     * @param role
     * @return int
     * @author zhuwj
     */
    int insert(Role role);

    /**
     * 批量插入权限表数据
     * @param dbName
     * @param permissionList
     * @return int
     * @author zhuwj
     */
    int insertPermissionBatch(@Param("dbName") String dbName, @Param("permissionList") List<Permission> permissionList);

    /**
     * 根据主键获取单个角色及其对应的权限
     * @param role
     * @return com.minstone.mobile.mp.identity.entity.Role
     * @author zhuwj
     */
    Role get(Role role);

    /**
     * 批量删除权限
     * @param permission
     * @return int
     * @author zhuwj
     */
    int deleteRolePermission(Permission permission);

    /**
     * 修改角色
     * @param role
     * @return int
     * @author zhuwj
     */
    int update(Role role);

    /**
     * 批量删除或还原角色
     * @param role
     * @return int
     * @author zhuwj
     */
    int deleteOrRevertBatch(Role role);

    /**
     * 条件查询角色列表
     * @param role
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.Role>
     * @author zhuwj
     */
    List<Role> findList(Role role);

    /**
     * 批量更新启用状态
     * @param role
     * @return int
     * @author zhuwj
     */
    int updateUseFlagBatch(Role role);
}

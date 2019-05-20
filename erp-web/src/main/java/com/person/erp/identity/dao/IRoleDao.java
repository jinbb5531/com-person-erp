package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.identity.entity.Permission;
import com.person.erp.identity.entity.Role;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuwj
 * @description
 * @since 2018/4/20
 */
@MyMapper
public interface IRoleDao extends BaseDao<Role> {
    
    /**
     * 新增角色
     * @param role
     * @return int
     * @author zhuwj
     */
    @Override
    @Options(useGeneratedKeys = true)
    long insert(Role role);

    /**
     * 批量插入权限表数据
     * @param permissionList
     * @return int
     * @author zhuwj
     */
    long insertPermissionBatch(@Param("permissionList") List<Permission> permissionList);

    /**
     * 根据主键获取单个角色及其对应的权限
     * @param id
     * @return com.minstone.mobile.mp.identity.entity.Role
     * @author zhuwj
     */
    Role getById(Long id);

    /**
     * 批量删除角色
     * @param ids
     * @return int
     * @author zhuwj
     */
    int deleteBatch(Long[] ids);

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

    /**
     * 根据角色id, 清除相关权限
     * @author zhuwj
     * @since 2019/5/18 10:56
     * @param roleId
     * @return long
     */
    long deleteRolePermission(Long roleId);

    /**
     * 根据角色ids, 清除相关权限
     * @author zhuwj
     * @since 2019/5/20 9:07
     * @param ids
     * @return long
     */
    long deleteRolePermissionBatch(Long[] ids);
}

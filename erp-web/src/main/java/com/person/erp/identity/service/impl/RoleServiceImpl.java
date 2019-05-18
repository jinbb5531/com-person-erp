package com.person.erp.identity.service.impl;

import com.itexplore.core.api.model.ApiException;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.dao.IRoleDao;
import com.person.erp.identity.entity.Permission;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.service.IRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>RoleServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/9 17:09
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public List<Role> findList(Role role) {
        return roleDao.findList(role);
    }

    @Override
    public long addRole(RoleDTO roleDTO) {

        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());

        User operator = TokenUtils.getUser();

        long systemTag = 0;
        int showFlag = WebConstant.ShowFlag.SHOW.getValue();

        if (operator != null && !TokenUtils.superManager()) {

            // 使用当前登录用户的标识
            systemTag = operator.getSystemTag();

        } else {

            showFlag = WebConstant.ShowFlag.HIDE.getValue();

        }

        role.setShowFlag(showFlag);
        role.setSystemTag(systemTag);
        role.setCreateBy(operator == null ? null : operator.getUserCode());
        role.setCreateAt(new Timestamp(new Date().getTime()));

        // 插入角色
        roleDao.insert(role);

        long roleId = role.getId();

        // 插入角色所分配的权限
        Long[] menuIds = roleDTO.getMenuIds();

        insertPermissionBatch(menuIds, roleId, operator == null ? null : operator.getUserCode());

        return roleId;
    }

    @Override
    public boolean insertPermissionBatch(Long[] menuIds, Long roleId, String createBy) {
        if (menuIds != null && menuIds.length > 0 && roleId != null) {

            List<Permission> permissionList = new ArrayList<>(menuIds.length);

            for (long menuId : menuIds) {

                Permission permission = new Permission();
                permission.setCreateBy(createBy);
                permission.setCreateAt(new Timestamp(new Date().getTime()));
                permission.setRoleId(roleId);
                permission.setMenuId(menuId);
                permissionList.add(permission);

            }

            // 插入权限
            return roleDao.insertPermissionBatch(permissionList) > 0;

        }
        return false;
    }

    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        Role oldRole = roleDao.findById(roleDTO.getId());

        if (oldRole == null) {
            return true;
        }

        if (!TokenUtils.superManager()) {

            if (Objects.equals(WebConstant.ShowFlag.HIDE.getValue(), oldRole.getShowFlag())) {

                // 隐藏角色只有超级管理员能改
                throw new ApiException(HttpStatus.FORBIDDEN, "无权修改隐藏角色");

            } else {
                // 非隐藏角色，不能修改为隐藏角色
                roleDTO.setShowFlag(null);
            }

        }

        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        role.setShowFlag(roleDTO.getShowFlag());
        role.setSystemTag(oldRole.getSystemTag());
        role.setUpdateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        // 1. 修改角色表
        boolean success = roleDao.updateBy(role) > 0;

        // 2. 清除该角色的权限
        roleDao.deletesRolePermission(role.getId());

        // 3. 添加角色的权限
        insertPermissionBatch(roleDTO.getMenuIds(), roleDTO.getId(), operator == null ? null : operator.getUserCode());

        return success;
    }
}

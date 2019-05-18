package com.person.erp.identity.service.impl;

import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.dao.IRoleDao;
import com.person.erp.identity.entity.Permission;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.awt.image.TileObserver;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        long[] menuIds = roleDTO.getMenuIds();

        if (menuIds != null && menuIds.length > 0) {

            List<Permission> permissionList = new ArrayList<>(menuIds.length);

            for (long menuId : menuIds) {

                Permission permission = new Permission();
                permission.setCreateBy(operator == null ? null : operator.getUserCode());
                permission.setCreateAt(new Timestamp(new Date().getTime()));
                permission.setRoleId(roleId);
                permission.setMenuId(menuId);
                permissionList.add(permission);

            }

            // 插入权限
            roleDao.insertPermissionBatch(permissionList);

        }

        return roleId;
    }
}

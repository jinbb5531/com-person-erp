package com.person.erp.identity.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.dao.IRoleDao;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.Permission;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.service.IRoleService;
import com.person.erp.identity.service.IUserService;
import org.springframework.beans.BeanUtils;
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

    @Resource
    private IUserService userService;

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

            // 未登录、超级管理员
//            showFlag = WebConstant.ShowFlag.HIDE.getValue();

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
        roleDao.deleteRolePermission(role.getId());

        // 3. 添加角色的权限
        insertPermissionBatch(roleDTO.getMenuIds(), roleDTO.getId(), operator == null ? null : operator.getUserCode());

        return success;
    }

    @Override
    public RoleDTO getRoleById(Long id) {

        Role role = roleDao.getById(id);


        if (role != null) {

            RoleDTO roleDTO = new RoleDTO();

            BeanUtils.copyProperties(role, roleDTO);

            List<Menu> permissionList = role.getMenuList();

            List<MenuDTO> menuDTOList = new ArrayList<>();

            List<Long> menuIdList = new ArrayList<>();

            if (!JudgeUtils.isEmpty(permissionList)) {

                permissionList.forEach(menu -> {

                    menuIdList.add(menu.getId());

                    MenuDTO menuDTO = new MenuDTO();

                    BeanUtils.copyProperties(menu, menuDTO);

                    menuDTOList.add(menuDTO);

                });

            }

            roleDTO.setMenuIds(menuIdList.toArray(new Long[0]));
            roleDTO.setMenus(menuDTOList);

            return roleDTO;

        }

        return null;
    }

    @Override
    public List<RoleDTO> findAll() {

        List<Role> list = findListByIdentity(null);

        List<RoleDTO> roleDTOList = new ArrayList<>(list.size());

        list.forEach(r -> {

            RoleDTO roleDTO = new RoleDTO();

            BeanUtils.copyProperties(r, roleDTO);

            roleDTOList.add(roleDTO);

        });

        return roleDTOList;
    }

    @Override
    public PageInfo<Role> findPage(String roleName, Pager pager) {

        Role role = new Role();

        if (!JudgeUtils.isTrimEmpty(roleName)) {
            role.setRoleName(roleName);
        }

        PageInfo<Object> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(findListByIdentity(role));
    }

    @Override
    public boolean deleteBatch(Long[] ids) {

        List<Role> roleList = roleDao.findByIds(ids, "id asc");

        if (JudgeUtils.isEmpty(roleList)) {
            return true;
        }

        validatedServicePermission(roleList);

        // 1.删除角色
        boolean success = roleDao.deleteBatch(ids) > 0;

        // 2.删除角色菜单
        roleDao.deleteRolePermissionBatch(ids);

        // 3.删除用户角色
        userService.deleteUserRoleBatchByRoleIds(ids);

        return success;
    }

    /**
     * 业务权限校验
     * @author zhuwj
     * @since 2019/5/20 8:34
     * @param roleList
     */
    private void validatedServicePermission(List<Role> roleList) {
        if (!TokenUtils.superManager() && !JudgeUtils.isEmpty(roleList)) {

            User operator = TokenUtils.getUser();

            Long systemTag = operator == null ? WebConstant.SUPER_MANAGER_TAG : operator.getSystemTag();

            roleList.forEach(role -> {

                if (Objects.equals(role.getShowFlag(), WebConstant.ShowFlag.HIDE.getValue())) {

                    throw new ApiException(HttpStatus.FORBIDDEN, "无权操作隐藏角色");

                } else if (!Objects.equals(role.getSystemTag(), systemTag)) {

                    throw new ApiException(HttpStatus.FORBIDDEN, "无权操作其他系统角色");

                }

            });

        }
    }

    /**
     * 根据是否超级管理员，来查询不同的角色列表
     * @author zhuwj
     * @since 2019/5/19 10:07
     * @param role
     * @return java.util.List<com.person.erp.identity.entity.Role>
     */
    private List<Role> findListByIdentity(Role role) {

        if (role == null) {

            role = new Role();

        }

        User operator = TokenUtils.getUser();

        // 查询同一个系统中的角色数据
        role.setSystemTag(operator == null ? 0 : operator.getSystemTag());

        if (!TokenUtils.superManager()) {

            // 非超级管理员，查询 “显示” 状态的角色
            role.setShowFlag(WebConstant.ShowFlag.SHOW.getValue());

        }

        return roleDao.findList(role);

    }
}

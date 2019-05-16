package com.person.erp.identity.service.impl;

import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.dao.IUserDao;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.entity.UserRole;
import com.person.erp.identity.model.UserDTO;
import com.person.erp.identity.service.IRoleService;
import com.person.erp.identity.service.IUserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>UserServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:11
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    @Resource
    private IRoleService roleService;

    @Override
    public boolean addUser(UserDTO userDTO) {

        User user = new User();

        BeanUtils.copyProperties(userDTO, user);

        user.setCreateAt(new Timestamp(new Date().getTime()));

        User operateUser = TokenUtils.getUser();

        user.setCreateBy(operateUser == null ? null : operateUser.getUserCode());

        if (operateUser != null && operateUser.getSystemTag() != 0) {

            // 存在操作者且操作者不是超级管理员, 则使用相同的系统标识
            user.setSystemTag(operateUser.getSystemTag());

        }

        // 校验是否存在该用户
        boolean exist = existUser(user.getUserCode(), user.getSystemTag());

        if (exist) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "该用户已存在，无法再次创建");
        } else if (!JudgeUtils.isEmpty(user.getMobilePhone())) {
            User phoneUser = new User();
            phoneUser.setMobilePhone(user.getMobilePhone());
            phoneUser.setSystemTag(user.getSystemTag());
            if (userDao.countUser(phoneUser) > 0) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "该手机号已被占用");
            }
        }

        // 保存用户角色
        long[] roleIds = userDTO.getRoleIds();
        if (roleIds != null && roleIds.length > 0) {

            List<UserRole> list = new ArrayList<>();

            for (long roleId : roleIds) {

                UserRole userRole = new UserRole();
                userRole.setRoleCode(roleId);
                userRole.setUserCode(user.getUserCode());
                userRole.setSystemTag(user.getSystemTag());
                list.add(userRole);

            }

            userDao.insertUserRoleBatch(list);

        }

        // 保存用户
        return userDao.insert(user) > 0;

    }

    @Override
    public boolean existUser(String userCode, long systemTag) {

        User user = new User();
        user.setUserCode(userCode);
        user.setSystemTag(systemTag);

        return userDao.countUser(user) > 0;

    }

    @Override
    public User getUser(String code, long systemTag) {

        User user = new User();
        user.setSystemTag(systemTag);
        user.setUserCode(code);

        return userDao.get(user);

    }

    @Override
    public UserDTO getUserDTO(String code, long systemTag) {
        return null;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {

        User user = getUser(userDTO.getUserCode(), userDTO.getSystemTag());

        if (user == null) {

            throw new ApiException(HttpStatus.NOT_FOUND, "该用户不存在");

        } else if (!JudgeUtils.isEmpty(userDTO.getMobilePhone())
                    && !userDTO.getMobilePhone().equals(user.getMobilePhone())) {

            // 手机号不为空，且不是以前的手机号，则检测一下别人有没有用过
            User phoneUser = new User();
            phoneUser.setSystemTag(userDTO.getSystemTag());
            phoneUser.setMobilePhone(userDTO.getMobilePhone());

            if (userDao.countUser(phoneUser) > 0) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "该手机号已被占用");
            }

        }

        // 待处理的角色主键集
        List<Long> roleIdList = new ArrayList<>();
        roleIdList.addAll(CollectionUtils.arrayToList(userDTO.getRoleIds()));

        if (!TokenUtils.superManager()) {

            // 非超级管理员，不能分配隐藏的角色

            // a.先判断有无分配隐藏角色，若有分配隐藏角色，则报异常

            // 查出数据库中所有的 隐藏角色
            Role role = new Role();
            role.setShowFlag(WebConstant.ShowFlag.HIDE.getValue());
            role.setSystemTag(WebConstant.SUPER_MANAGER_TAG);
            List<Role> hideRoleList = roleService.findList(role);

            if (!JudgeUtils.isEmpty(hideRoleList)) {
                hideRoleList.forEach(hideRole -> {

                    for (long roleId : roleIdList) {
                        if (roleId == hideRole.getId()) {
                            throw new ApiException(HttpStatus.BAD_REQUEST, "不能分配隐藏角色");
                        }
                    }

                });
            }

            // b. 查出当前用户的隐藏角色，待会清空后，再重新插入隐藏角色。
            // 目前已拥有的所有角色
            List<Role> roleList = user.getRoleList();

            // 已拥有的隐藏角色
            List<Long> haveHideRoleIdList = new ArrayList<>();

            roleList.forEach(r -> {

                if (r.getShowFlag() == WebConstant.ShowFlag.HIDE.getValue()) {
                    haveHideRoleIdList.add(r.getId());
                }

            });

            roleIdList.addAll(haveHideRoleIdList);

        }

        // 清空目前拥有的角色
        userDao.deleteUserRoleByUser(userDTO.getUserCode(), userDTO.getSystemTag());

        // 重新插入用户角色
        List<UserRole> newUserRoleList = new ArrayList<>();

        for (Long roleId : roleIdList) {
            UserRole userRole = new UserRole();
            userRole.setSystemTag(userDTO.getSystemTag());
            userRole.setUserCode(userDTO.getUserCode());
            userRole.setRoleCode(roleId);
            newUserRoleList.add(userRole);
        }

        if (newUserRoleList.size() > 0) {
            userDao.insertUserRoleBatch(newUserRoleList);
        }

        return userDao.update(user) > 0;
    }

    @Override
    public boolean deleteBatch(String[] codes, long systemTag) {
        return userDao.deleteBatch(codes, systemTag) > 0;
    }

    @Override
    public boolean deleteBatch(@NotNull List<UserDTO> userList) {

        boolean success = false;

        if (TokenUtils.superManager()) {

            // 超级管理员，可直接删除所传用户
            List<User> list = new ArrayList<>(userList.size());

            userList.forEach(userDTO -> {
                User user = new User();
                user.setUserCode(userDTO.getUserCode());
                user.setSystemTag(userDTO.getSystemTag());
                list.add(user);
            });

            success = userDao.deleteBatchByUser(list) > 0;

        } else {

            // 非超级管理员，只能删除自己系统内的用户
            String[] codes = new String[userList.size()];

            for (int i = 0; i < userList.size(); i++) {

                codes[i] = userList.get(i).getUserCode();

            }

            success = userDao.deleteBatch(codes, TokenUtils.getUser() == null ? 0 : TokenUtils.getUser().getSystemTag()) > 0;

        }

        // 删除成功后，将对应用户集的 token 从 Redis 中清空
        for (UserDTO userDTO : userList) {

            User user = new User();
            user.setSystemTag(userDTO.getSystemTag());
            user.setUserCode(userDTO.getUserCode());

            TokenUtils.removeUser(user);

        }

        return success;

    }

}

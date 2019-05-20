package com.person.erp.identity.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.UserDTO;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * <p>IUserService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:10
 */
public interface IUserService {

    /**
     * 添加用户
     * @author zhuwj
     * @since 2019/5/9 12:09
     * @param userDTO
     * @return boolean
     */
    boolean addUser(UserDTO userDTO);

    /**
     * 校验用户是否已存在
     * @author zhuwj
     * @since 2019/5/9 14:49
     * @param userCode
     * @param systemTag
     * @return boolean
     */
    boolean existUser(String userCode, long systemTag);

    /**
     * 获取用户信息
     * @author zhuwj
     * @since 2019/5/9 13:47
     * @param code
     * @param systemTag
     * @return com.person.erp.identity.entity.User
     */
    User getUser(String code, long systemTag);

    /**
     * 获取用户信息及角色信息
     * @author zhuwj
     * @since 2019/5/9 13:46
     * @param code
     * @param systemTag
     * @return com.person.erp.identity.model.UserDTO
     */
    UserDTO getUserDTO(String code, long systemTag);

    /**
     * 更新用户
     * @author zhuwj
     * @since 2019/5/9 16:41
     * @param userDTO
     * @return boolean
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 批量删除用户, userDTO中，userCode与systemTag不能为空。
     * 超级管理员，可直接删除所传用户；非超级管理员，只能删除自己系统内的用户
     * @author zhuwj
     * @since 2019/5/16 16:10
     * @param userList
     * @return boolean
     */
    boolean deleteBatch(@NotNull List<UserDTO> userList);

    /**
     * 按条件分页查找用户
     * @author zhuwj
     * @since 2019/5/17 11:15
     * @param userDTO
     * @param pager
     * @return com.github.pagehelper.PageInfo<com.person.erp.identity.entity.User>
     */
    PageInfo<User> findPage(UserDTO userDTO, Pager pager);

    /**
     * 通过角色ids, 批量删除用户角色
     * @author zhuwj
     * @since 2019/5/20 9:19
     * @param ids
     * @return boolean
     */
    boolean deleteUserRoleBatchByRoleIds(Long[] ids);
}

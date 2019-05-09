package com.person.erp.identity.service;

import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.UserDTO;

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
}

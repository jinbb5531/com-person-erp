package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.entity.UserRole;
import com.person.erp.identity.model.UserDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuwj
 * @description
 * @since 2018/4/24
 */
@MyMapper
public interface IUserDao {
    /**
     * 批量插入用户角色数据
     * @param userRoleList
     * @return int
     * @author zhuwj
     */
    int insertUserRoleBatch(@Param("userRoleList") List<UserRole> userRoleList);

    /**
     * 插入用户
     * @param user
     * @return int
     * @author zhuwj
     */
    int insert(User user);

    /**
     * 统计该条件的用户的数量
     * @param user
     * @return int
     * @author zhuwj
     */
    int countUser(User user);

    /**
     * 根据userCode、systemTag获取用户及用户角色、用户部门中间表
     * @param user
     * @return com.minstone.mobile.mp.identity.entity.User
     * @author zhuwj
     */
    User get(User user);

    /**
     * 修改用户
     * @param user
     * @return boolean
     * @author zhuwj
     */
    int update(User user);

    /**
     * 批量修改用户状态
     * @param user
     * @return int
     * @author zhuwj
     */
    int updateStatusBatch(User user);

    /**
     * 批量修改用户密码
     * @param userList
     * @return int
     * @author zhuwj
     */
    int updatePasswordBatch(@Param("userList") List<User> userList);

    /**
     * 查找用户角色中间表
     * @param roleCodes
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.UserRole>
     * @author zhuwj
     */
    List<UserRole> findUserRoleList(@Param("roleCodes") String[] roleCodes);

    /**
     * 查找用户角色中间表
     * @param roleCodes
     * @param codes
     * @param systemTag
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.UserRole>
     * @author zhuwj
     */
    List<UserRole> findUserRoleList(@Param("roleCodes") String[] roleCodes, @Param("codes") String[] codes, @Param("systemTag") String systemTag);

    /**
     * 条件查找用户
     * @param user
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.User>
     * @author zhuwj
     */
    List<User> findList(User user);

    /**
     * 条件查找用户关联上角色
     * @param user
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.User>
     * @author zhuwj
     */
    List<User> findListUserAssociateRole(User user);

    /**
     * 条件查找用户关联上部门
     * @param user
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.User>
     * @author zhuwj
     */
    List<User> findListUserAssociateDept(User user);

    /**
     * 清空相关用户角色
     * @author zhuwj
     * @since 2019/5/9 18:41
     * @param userCode
     * @param systemTag
     * @return int
     */
    int deleteUserRoleByUser(@Param("userCode") String userCode, @Param("systemTag") long systemTag);

    /**
     * 批量删除用户
     * @author zhuwj
     * @since 2019/5/16 16:02
     * @param codes
     * @param systemTag
     * @return boolean
     */
    int deleteBatch(@Param("codes") String[] codes, @Param("systemTag") long systemTag);

    /**
     * 批量删除用户
     * @author zhuwj
     * @since 2019/5/16 16:48
     * @param userList
     * @return int
     */
    int deleteBatchByUsers(List<User> userList);

    /**
     * 批量删除用户角色中间表
     * @author zhuwj
     * @since 2019/5/17 9:17
     * @param userList
     * @return int
     */
    int deleteUserRoleByUsers(List<User> userList);

    /**
     * 批量删除用户角色中间表
     * @author zhuwj
     * @since 2019/5/17 9:22
     * @param codes
     * @param systemTag
     * @return int
     */
    int deleteUserRoleBatch(@Param("codes") String[] codes, @Param("systemTag") long systemTag);

    /**
     * 关联查询用户
     * @author zhuwj
     * @since 2019/5/17 12:35
     * @param systemTag
     * @param roleIds
     * @param showFlag
     * @param status
     * @param workKind
     * @param userName
     * @return java.util.List<com.person.erp.identity.entity.User>
     */
    List<User> findListUserAssociateRole(@Param("systemTag") Long systemTag,
                                         @Param("roleIds") Long[] roleIds,
                                         @Param("showFlag") Integer showFlag,
                                         @Param("status") Integer status,
                                         @Param("workKind") String workKind,
                                         @Param("userName") String userName);

    /**
     * 通过角色ids, 批量删除用户角色
     * @author zhuwj
     * @since 2019/5/20 9:23
     * @param ids
     * @return long
     */
    long deleteUserRoleBatchByRoleIds(Long[] ids);

    /**
     * 通过的手机号获取用户
     * @author zhuwj
     * @since 2019/5/25 10:30
     * @param mobilePhone
     * @return com.person.erp.identity.entity.User
     */
    User getUserByPhone(@Param("mobilePhone") String mobilePhone);
}

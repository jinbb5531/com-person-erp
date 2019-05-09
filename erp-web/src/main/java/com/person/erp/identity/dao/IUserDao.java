package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.entity.UserRole;
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
     * 批量删除或还原用户
     * @param user
     * @return int
     * @author zhuwj
     */
    int deleteOrRevertBatch(User user);

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
    int deleteUserRoleByUser(String userCode, long systemTag);
}

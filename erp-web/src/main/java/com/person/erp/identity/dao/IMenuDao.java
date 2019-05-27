package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zhuwj
 * @description
 * @since 2018/4/19
 */
@MyMapper
public interface IMenuDao {
    /**
     * 插入一条菜单数据
     * @param menu
     * @return int
     * @author zhuwj
     */
    int insert(Menu menu);

    /**
     * 统计出parentCode和systemTag下最大的排序号
     * @param menu
     * @return java.lang.Integer
     * @author zhuwj
     */
    Integer maxQueueByParentCode(Menu menu);

    /**
     *
     * @param menu
     * @return int
     * @author zhuwj
     */
    int update(Menu menu);

    /**
     * 删除或还原codes中的数据
     * @param menu
     * @return int
     * @author zhuwj
     */
    int deleteOrRevertBatch(Menu menu);

    /**
     * 按条件选出其所有菜单
     * @param menu
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.Menu>
     * @author zhuwj
     */
    List<Menu> findList(Menu menu);

    /**
     * 批量更新启用状态
     * @param menu
     * @return int
     * @author zhuwj
     */
    int updateUseFlagBatch(Menu menu);

    /**
     * 获取当前用户所有的权限
     * @param user
     * @return java.util.List<com.minstone.mobile.mp.identity.entity.Menu>
     * @author zhuwj
     */
    List<Menu> getPermissionListByUser(User user);

    /**
     * 批量插入菜单
     * @param menuList
     * @return long
     * @author zhuwj
     */
    long insertMenuBatch(@Param("menuList") List<Menu> menuList);

    /**
     * 通过主键查询菜单，并封装其父
     * @author zhuwj
     * @since 2019/5/22 9:40
     * @param id
     * @return com.person.erp.identity.entity.Menu
     */
    Menu getMenuById(@Param("id") Long id);

    /**
     * 批量删除菜单
     * @author zhuwj
     * @since 2019/5/22 11:42
     * @param ids
     * @return long
     */
    long deletes(Long[] ids);

    /**
     * 通过菜单主键集批量删除角色权限
     * @author zhuwj
     * @since 2019/5/22 11:43
     * @param ids
     * @return long
     */
    long deleteRolePermissionBatch(Long[] ids);

    /**
     * 通过角色主键集获取所有权限
     * @author zhuwj
     * @since 2019/5/24 23:11
     * @param roleIds
     * @return java.util.List<com.person.erp.identity.entity.Menu>
     */
    List<Menu> getPermissionListByRoleIds(Long[] roleIds);

    /**
     * 通过url查找
     * @author zhuwj
     * @since 2019/5/27 15:32
     * @param menuUrl
     * @return com.person.erp.identity.entity.Menu
     */
    Menu getMenuByUrl(@Param("menuUrl") String menuUrl);
}

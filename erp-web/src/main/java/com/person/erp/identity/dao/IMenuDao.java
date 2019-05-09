package com.person.erp.identity.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.User;

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
     * 获取单条菜单
     * @param menu
     * @return com.minstone.mobile.mp.identity.entity.Menu
     * @author zhuwj
     */
    Menu get(Menu menu);

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
     * @param menu
     * @return boolean
     * @author zhuwj
     */
    boolean insertMenuBatch(Menu menu);
}

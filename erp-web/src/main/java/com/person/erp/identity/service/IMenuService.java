package com.person.erp.identity.service;

import com.person.erp.identity.model.MenuDTO;

import java.util.List;

/**
 * <p>IMenuService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/22 9:16
 */
public interface IMenuService {

    /**
     * 新增菜单
     * @author zhuwj
     * @since 2019/5/22 9:17
     * @param menuDTO
     * @return java.lang.Long
     */
    Long addMenu(MenuDTO menuDTO);

    /**
     * 获取菜单
     * @author zhuwj
     * @since 2019/5/22 9:36
     * @param id
     * @return com.person.erp.identity.model.MenuDTO
     */
    MenuDTO getMenuById(Long id);

    /**
     * 修改菜单
     * @author zhuwj
     * @since 2019/5/22 9:51
     * @param menuDTO
     * @return boolean
     */
    boolean updateMenu(MenuDTO menuDTO);

    /**
     * 查出整棵菜单树
     * @author zhuwj
     * @since 2019/5/22 10:06
     * @param
     * @return java.util.List<com.person.erp.identity.model.MenuDTO>
     */
    List<MenuDTO> findMenuTree();

    /**
     * 删除菜单及其所有的子菜单、权限表
     * @author zhuwj
     * @since 2019/5/22 10:50
     * @param id
     *
     * @return boolean
     */
    boolean delete(Long id);
}

package com.person.erp.identity.service.impl;

import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.itexplore.core.common.utils.reference.CollectionUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.PojoChangeUtils;
import com.person.erp.common.utils.RelationUtils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.constant.IdentityConstant;
import com.person.erp.identity.dao.IMenuDao;
import com.person.erp.identity.dao.IRoleDao;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.service.IMenuService;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>IMenuServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/22 9:17
 */
@Service
@Transactional
public class MenuServiceImpl implements IMenuService {

    @Resource
    private IMenuDao menuDao;

    @Override
    public Long addMenu(MenuDTO menuDTO) {

        Menu menu = new Menu();

        BeanUtils.copyProperties(menuDTO, menu);

        // 默认为模块
        if (menu.getModuleFlag() == null) {
            menu.setModuleFlag(IdentityConstant.ModuleFlag.MODULE.getValue());
        }

        // 默认为显示
        if (menu.getShowFlag() == null) {
            menu.setModuleFlag(WebConstant.ShowFlag.SHOW.getValue());
        }

        // 默认为启用
        if (menu.getUseFlag() == null) {
            menu.setUseFlag(IdentityConstant.UseFlag.USE.getValue());
        }

        // 菜单都设置为显示
        menu.setShowFlag(WebConstant.ShowFlag.SHOW.getValue());

        menu.setCreateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        menu.setCreateBy(operator == null ? null : operator.getUserCode());

        menuDao.insert(menu);

        return menu.getId();

    }

    @Override
    public MenuDTO getMenuById(Long id) {

        Menu menu = menuDao.getMenuById(id);

        MenuDTO dto = null;

        if (menu != null) {

            dto = new MenuDTO();

            BeanUtils.copyProperties(menu, dto);

            Menu parentMenu = menu.getParentMenu();

            dto.setParentName(parentMenu == null ? null : parentMenu.getMenuName());

        }

        return dto;
    }

    @Override
    public boolean updateMenu(MenuDTO menuDTO) {

        MenuDTO oldMenu = getMenuById(menuDTO.getId());

        if (oldMenu == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "该菜单不存在，无法修改");
        }

        Menu menu = new Menu();

        BeanUtils.copyProperties(menuDTO, menu);

        menu.setUpdateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        menu.setUpdateBy(operator == null ? null : operator.getUserCode());

        return menuDao.update(menu) > 0;
    }

    @Override
    public List<MenuDTO> findMenuTree() {

        List<Menu> list = menuDao.findList(null);

        return orderRankRelation(list);

    }

    @Override
    public boolean delete(Long id) {

        // a.找出所有子菜单, 删除
        List<Menu> list = menuDao.findList(null);

        if (JudgeUtils.isEmpty(list)) {
            return true;
        }

        Long[] ids = getAllChildMenuCodes(new Long[]{id}, list);

        // b.删除权限表
        menuDao.deleteRolePermissionBatch(ids);

        return menuDao.deletes(ids) > 0;

    }

    @Override
    public List<MenuDTO> getPermissionByRoleIds(Long[] roleIds) {

        List<Menu> menuList = menuDao.getPermissionListByRoleIds(roleIds);

        return orderRankRelation(menuList);

    }

    /**
     * 从list中筛选出codes的所有子菜单主键
     * @param codes
     * @param list
     * @return java.lang.String[]
     * @author zhuwj
     */
    private Long[] getAllChildMenuCodes(Long[] codes, List<Menu> list) {

        if (list == null || list.size() < 1 || codes == null || codes.length < 1) {
            return codes;
        }

        // 转为成以父主键为key，相应子菜单主键集为value的map
        Map<Long, List<Long>> allParentMap = new LinkedHashMap<>();

        for (Menu menu : list) {

            Long parentId = menu.getParentId();

            if (allParentMap.get(parentId) == null) {

                List<Long> childList = new ArrayList<>();

                childList.add(menu.getId());

                allParentMap.put(parentId, childList);

            } else {

                allParentMap.get(parentId).add(menu.getId());

            }

        }

        // 开始匹配这些codes是否有子菜单了
        Set<Long> set = new HashSet<>();
        set.addAll(Arrays.asList(codes));

        List<Long> allCodes = RelationUtils.searchHaveChildCode(CollectionUtils.toList(set), allParentMap);

        return allCodes.toArray(new Long[]{});
    }

    private List<MenuDTO> orderRankRelation(List<Menu> list) {
        if (JudgeUtils.isEmpty(list)) {
            return null;
        }
        
        // 转换
        List<MenuDTO> dtoList = new ArrayList<>(list.size());
        PojoChangeUtils.copyEntityList2DTOList(list, dtoList, MenuDTO.class);
        
        // key: 父主键；value：所有子菜单集
        Map<Long, List<MenuDTO>> allParentMap = new LinkedHashMap<>();
        
        // 存放无父主键的父菜单
        List<MenuDTO> parentList = new ArrayList<>();
        
        for (MenuDTO menu : dtoList) {
            
            Long parentId = menu.getParentId();

            if (parentId == null) {

                // 无父主键的，放入 parentList
                parentList.add(menu);

            } else if (allParentMap.get(parentId) == null) {

                // allParentMap中还没放入的，创建一个子集，放入一个子元素
                List<MenuDTO> childList = new ArrayList<>();

                childList.add(menu);

                allParentMap.put(parentId, childList);

            } else {

                // 不为空，直接取出该父的子集，加入一个子元素
                allParentMap.get(parentId).add(menu);

            }
            
        }

        for (MenuDTO menu : dtoList) {

            // 找出相应的子菜单集，放入childMenuList属性中
            Long id = menu.getId();

            List<MenuDTO> childList = allParentMap.get(id);

            if (childList != null) {

                menu.setChildMenus(childList);
                allParentMap.remove(id);

            }

        }

        // 若还有未处理完的菜单，则说明这些菜单的父菜单已经被删除了而子菜单却未删除。（一般不存在这些数据，除非有人动了数据库）
        if (allParentMap.size() > 0) {
            for (MenuDTO menu : dtoList) {
                for (Long parentId : allParentMap.keySet()) {
                    if (parentId.equals(menu.getParentId())) {
                        parentList.add(menu);
                        break;
                    }
                }
            }
        }

        return parentList;
    }

}

package com.person.erp.system.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.system.entity.ChildSystem;
import com.person.erp.system.model.ChildSystemDTO;

import java.util.List;

/**
 * <p>ISystemService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:44
 */
public interface ISystemService {

    /**
     * 添加
     * @author zhuwj
     * @since 2019/5/30 23:46
     * @param dto
     * @return java.lang.Long
     */
    Long add(ChildSystemDTO dto);

    /**
     * 获取
     * @author zhuwj
     * @since 2019/5/30 23:46
     * @param id
     * @return ChildSystem
     */
    ChildSystem getById(Long id);

    /**
     * 修改
     * @author zhuwj
     * @since 2019/5/30 23:47
     * @param dto
     * @return boolean
     */
    boolean update(ChildSystemDTO dto);

    /**
     * 条件分页
     * @author zhuwj
     * @since 2019/5/30 23:47
     * @param dto
     * @param pager
     * @return com.github.pagehelper.PageInfo<com.person.erp.system.entity.ChildSystem>
     */
    PageInfo<ChildSystem> findPage(ChildSystemDTO dto, Pager pager);

    /**
     * 删除
     * @author zhuwj
     * @since 2019/5/30 23:47
     * @param id
     * @return boolean
     */
    boolean delete(Long id);

    /**
     * 条件查找
     * @author zhuwj
     * @since 2019/5/31 17:53
     * @param dto
     * @return java.util.List<com.person.erp.system.entity.ChildSystem>
     */
    List<ChildSystem> findList(ChildSystemDTO dto);
}

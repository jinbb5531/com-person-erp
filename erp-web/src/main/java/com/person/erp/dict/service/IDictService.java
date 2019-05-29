package com.person.erp.dict.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.dict.entity.Dict;
import com.person.erp.dict.entity.DictType;
import com.person.erp.dict.model.DictDTO;

import java.util.List;

/**
 * <p>IDictService.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:19
 */
public interface IDictService {

    /**
     * 添加类型
     * @author zhuwj
     * @since 2019/5/20 16:19
     * @param typeName
     * @return java.lang.Long
     */
    Long addType(String typeName);

    /**
     * 检查类型是否已存在
     * @author zhuwj
     * @since 2019/5/20 16:22
     * @param typeName
     * @return boolean
     */
    boolean existType(String typeName);

    /**
     * 查找所有字典类型
     * @author zhuwj
     * @since 2019/5/20 16:34
     * @return java.util.List<com.person.erp.dict.entity.DictType>
     */
    List<DictType> findAllType();

    /**
     * 通过名字查找字典类型
     * @author zhuwj
     * @since 2019/5/20 16:37
     * @param typeName
     * @return com.person.erp.dict.entity.DictType
     */
    DictType findTypeByName(String typeName);

    /**
     * 通过主键查找字典类型
     * @author zhuwj
     * @since 2019/5/20 16:43
     * @param id
     * @return com.person.erp.dict.entity.DictType
     */
    DictType findTypeById(Long id);

    /**
     * 新增字典
     * @author zhuwj
     * @since 2019/5/21 9:53
     * @param dictDTO
     * @return java.lang.String
     */
    String addDict(DictDTO dictDTO);

    /**
     * 通过主键，获取字典
     * @author zhuwj
     * @since 2019/5/21 10:10
     * @param id
     * @return com.person.erp.dict.model.DictDTO
     */
    DictDTO getDict(String id);

    /**
     * 修改字典
     * @author zhuwj
     * @since 2019/5/21 10:18
     * @param dictDTO
     * @return boolean
     */
    boolean updateDict(DictDTO dictDTO);

    /**
     * 条件查询
     * @author zhuwj
     * @since 2019/5/21 10:34
     * @param dictDTO
     * @return java.util.List<com.person.erp.dict.model.DictDTO>
     */
    List<DictDTO> findDictList(DictDTO dictDTO);

    /**
     * 分页查询字典
     * @author zhuwj
     * @since 2019/5/21 14:47
     * @param dictDTO
     * @param pager
     * @return com.github.pagehelper.PageInfo<com.person.erp.dict.model.DictDTO>
     */
    PageInfo<Dict> findPage(DictDTO dictDTO, Pager pager);

    /**
     * 批量删除字典
     * @author zhuwj
     * @since 2019/5/21 15:10
     * @param ids
     * @return boolean
     */
    boolean deletes(String[] ids);
}

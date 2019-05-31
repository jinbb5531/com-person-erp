package com.person.erp.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.dict.dao.IDictDao;
import com.person.erp.dict.dao.IDictTypeDao;
import com.person.erp.dict.entity.Dict;
import com.person.erp.dict.entity.DictType;
import com.person.erp.dict.model.DictDTO;
import com.person.erp.dict.service.IDictService;
import com.person.erp.identity.entity.User;
import lombok.experimental.Tolerate;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * <p>DictServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:19
 */
@Service
@Transactional
public class DictServiceImpl implements IDictService {

    @Resource
    private IDictDao dictDao;

    @Resource
    private IDictTypeDao dictTypeDao;

    @Override
    public Long addType(String typeName) {

        boolean exist = existType(typeName);

        if (exist) {

            throw new ApiException(HttpStatus.BAD_REQUEST, "该字典类型已存在");

        }

        DictType dictType = new DictType(typeName);

        dictTypeDao.insert(dictType);

        return dictType.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existType(String typeName) {

        DictType dictType = new DictType(typeName);

        return dictTypeDao.existsBy(dictType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DictType> findAllType() {
        return dictTypeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DictType findTypeByName(String typeName) {

        if (JudgeUtils.isEmpty(typeName)) {
            return null;
        }

        DictType dictType = new DictType(typeName);

        return dictTypeDao.findFirstBy(dictType);
    }

    @Override
    public DictType findTypeById(Long id) {
        return dictTypeDao.findById(id);
    }

    @Override
    public String addDict(DictDTO dictDTO) {

        Dict dbDict = dictDao.findById(dictDTO.getId());

        if (dbDict != null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "该字典主键已被占用");
        }

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        // 设置字典类型外键
        dict.setTypeId(findTypeIdOtherwiseCreate(dictDTO.getTypeName()));

        dict.setCreateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        dict.setSystemTag(operator == null ? WebConstant.SUPER_MANAGER_TAG : operator.getSystemTag());

        dict.setCreateBy(operator == null ? null : operator.getUserCode());

        dictDao.insert(dict);

        return dict.getId();
    }

    private Long findTypeIdOtherwiseCreate(String typeName) {

        DictType type = findTypeByName(typeName);

        if (type == null) {
            // 字典类型为空，则创建一个
            return addType(typeName);
        } else {
            return type.getId();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public DictDTO getDict(String id) {

        Dict dict = dictDao.findById(id);
        DictDTO dto = null;

        if (dict != null) {

            dto = new DictDTO();

            BeanUtils.copyProperties(dict, dto);

            DictType dictType = dictTypeDao.findById(dict.getTypeId());

            if (dictType != null) {

                dto.setTypeName(dictType.getTypeName());

            }

        }

        return dto;

    }

    @Override
    public boolean updateDict(DictDTO dictDTO) {

        Dict dbDict = dictDao.findById(dictDTO.getId());

        User operator = TokenUtils.getUser();

        if (dbDict == null) {

            throw new ApiException(HttpStatus.NOT_FOUND, "字典已不存在，无法修改");

        } else if (operator != null && !Objects.equals(operator.getSystemTag(), dbDict.getSystemTag())) {

            throw new ApiException(HttpStatus.UNAUTHORIZED, "您无权修改其他子系统数据");

        }

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        dict.setTypeId(findTypeIdOtherwiseCreate(dictDTO.getTypeName()));

        dict.setUpdateAt(new Timestamp(new Date().getTime()));

        dict.setUpdateBy(operator == null ? null : operator.getUserCode());

        return dictDao.updateBy(dict) > 0;

    }

    @Override
    public List<DictDTO> findDictList(DictDTO dictDTO) {

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        User user = TokenUtils.getUser();

        dict.setSystemTag(user == null ? WebConstant.SUPER_MANAGER_TAG : user.getSystemTag());

        if (!JudgeUtils.isEmpty(dictDTO.getTypeName())) {

            DictType type = findTypeByName(dictDTO.getTypeName());

            if (type == null) {
                return Collections.emptyList();
            } else {
                dict.setTypeId(type.getId());
            }

        }

        List<Dict> dictList = dictDao.findListAssociateType(dict);

        return dealDictChange(dictList);

    }

    @Override
    public List<DictDTO> dealDictChange(List<Dict> dictList) {

        List<DictDTO> dtoList = new ArrayList<>();

        User user = TokenUtils.getUser();

        long systemTag = user == null ? WebConstant.SUPER_MANAGER_TAG : user.getSystemTag();

        dictList.forEach(d -> {

            DictDTO dto = new DictDTO();

            BeanUtils.copyProperties(d, dto);

            dto.setTypeName(d.getDictType().getTypeName());

            // 若当前用户的系统标识与数据的系统标识相同，则可以操作（0）；否则不能操作（1）
            dto.setFlag(systemTag == d.getSystemTag() ? 0 : 1);

            dtoList.add(dto);

        });

        return dtoList;

    }

    @Override
    public PageInfo<Dict> findPage(DictDTO dictDTO, Pager pager) {

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        User user = TokenUtils.getUser();

        dict.setSystemTag(user == null ? WebConstant.SUPER_MANAGER_TAG : user.getSystemTag());

        if (!JudgeUtils.isEmpty(dictDTO.getTypeName())) {

            DictType type = findTypeByName(dictDTO.getTypeName());

            dict.setTypeId(type == null ? -1L : type.getId());

        }

        PageInfo<DictDTO> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(dictDao.findListAssociateType(dict));

    }

    @Override
    public boolean deletes(String[] ids) {

        List<Dict> dictList = dictDao.findByIds(ids, " id asc ");

        User user = TokenUtils.getUser();

        long systemTag = user == null ? WebConstant.SUPER_MANAGER_TAG : user.getSystemTag();

        dictList.forEach(dict -> {

            if (dict.getSystemTag() != systemTag) {

                throw new ApiException(HttpStatus.UNAUTHORIZED, "您无权删除其他子系统数据");

            }

        });

        return dictDao.deleteByIds(ids) > 0;

    }

}

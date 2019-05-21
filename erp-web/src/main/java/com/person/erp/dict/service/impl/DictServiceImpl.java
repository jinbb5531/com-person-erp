package com.person.erp.dict.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public Long addDict(DictDTO dictDTO) {

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        dict.setCreateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        dict.setCreateBy(operator == null ? null : operator.getUserCode());

        dictDao.insert(dict);

        return dict.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public DictDTO getDict(Long id) {

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

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        dict.setUpdateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        dict.setUpdateBy(operator == null ? null : operator.getUserCode());

        return dictDao.updateBy(dict) > 0;

    }

    @Override
    public List<DictDTO> findDictList(DictDTO dictDTO) {

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        List<Dict> dictList = dictDao.findListAssociateType(dict);

        return dealDictChange(dictList);

    }

    private List<DictDTO> dealDictChange(List<Dict> dictList) {

        List<DictDTO> dtoList = new ArrayList<>();

        dictList.forEach(d -> {

            DictDTO dto = new DictDTO();

            BeanUtils.copyProperties(d, dto);

            dto.setTypeName(d.getDictType().getTypeName());

            dtoList.add(dto);

        });

        return dtoList;

    }

    @Override
    public PageInfo<Dict> findPage(DictDTO dictDTO, Pager pager) {

        Dict dict = new Dict();

        BeanUtils.copyProperties(dictDTO, dict);

        PageInfo<DictDTO> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(dictDao.findListAssociateType(dict));

    }

    @Override
    public boolean deletes(Long[] ids) {

        return dictDao.deleteByIds(ids) > 0;

    }

}

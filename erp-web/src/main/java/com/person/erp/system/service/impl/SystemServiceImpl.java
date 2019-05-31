package com.person.erp.system.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.system.constant.ChildSystemConstant;
import com.person.erp.system.dao.ISystemDao;
import com.person.erp.system.entity.ChildSystem;
import com.person.erp.system.model.ChildSystemDTO;
import com.person.erp.system.service.ISystemService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>SystemServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:45
 */
@Service
@Transactional
public class SystemServiceImpl implements ISystemService {

    @Resource
    private ISystemDao systemDao;

    @Override
    public Long add(ChildSystemDTO dto) {

        ChildSystem childSystem = new ChildSystem();

        BeanUtils.copyProperties(dto, childSystem);

        if (childSystem.getStatus() == null) {
            childSystem.setStatus(ChildSystemConstant.Status.USE.getValue());
        }

        systemDao.insert(childSystem);

        return childSystem.getId();

    }

    @Override
    public ChildSystem getById(Long id) {

        return systemDao.findById(id);

    }

    @Override
    public boolean update(ChildSystemDTO dto) {

        ChildSystem childSystem = new ChildSystem();
        
        BeanUtils.copyProperties(dto, childSystem);

        return systemDao.updateBy(childSystem) > 0;
        
    }

    @Override
    public PageInfo<ChildSystem> findPage(ChildSystemDTO dto, Pager pager) {

        ChildSystem childSystem = new ChildSystem();
        
        BeanUtils.copyProperties(dto, childSystem);

        PageInfo<ChildSystem> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<ChildSystem>(systemDao.findList(childSystem));
    }

    @Override
    public boolean delete(Long id) {
        return systemDao.deleteById(id) > 0;
    }

    @Override
    public List<ChildSystem> findList(ChildSystemDTO dto) {

        ChildSystem childSystem = new ChildSystem();

        if (dto != null) {
            BeanUtils.copyProperties(dto, systemDao);
        }

        return systemDao.findList(childSystem);
    }
}

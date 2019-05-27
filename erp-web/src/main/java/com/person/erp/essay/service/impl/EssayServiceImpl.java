package com.person.erp.essay.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.essay.constant.EssayConstant;
import com.person.erp.essay.controller.EssayController;
import com.person.erp.essay.dao.IContentDao;
import com.person.erp.essay.dao.IEssayDao;
import com.person.erp.essay.entity.Content;
import com.person.erp.essay.entity.Essay;
import com.person.erp.essay.model.EssayDTO;
import com.person.erp.essay.service.IEssayService;
import com.person.erp.identity.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>EssayServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:29
 */
@Service
@Transactional
public class EssayServiceImpl implements IEssayService {

    @Resource
    private IEssayDao essayDao;

    @Resource
    private IContentDao contentDao;

    @Override
    public Long add(EssayDTO essayDTO) {

        Essay essay = new Essay();

        BeanUtils.copyProperties(essayDTO, essay);

        essay.setQueueNum(EssayConstant.NORMAL);

        essay.setStatus(EssayConstant.Status.DRAFT.getValue());

        User user = TokenUtils.getUser();

        essay.setCreateBy(user == null ? null : user.getMobilePhone());

        essay.setCreateAt(new Timestamp(new Date().getTime()));

        essayDao.insert(essay);

        // 插入内容表
        Content content = new Content(essay.getId(), essayDTO.getContent());

        contentDao.insert(content);

        return essay.getId();

    }

    @Override
    public EssayDTO getById(Long id) {

        EssayDTO essayDTO = null;
        Essay essay = essayDao.findById(id);

        if (essay != null) {

            essayDTO = new EssayDTO();

            BeanUtils.copyProperties(essay, essayDTO);

            Content content = contentDao.findById(id);

            if (content != null) {

                essayDTO.setContent(content.getMainContent());

            }

        }

        return essayDTO;

    }

    @Override
    public boolean update(EssayDTO essayDTO) {

        Essay dbEssay = essayDao.findById(essayDTO.getId());

        if (dbEssay == null) {

            throw new ApiException(HttpStatus.NOT_FOUND, "文章不存在，无法修改");

        }

        Essay essay = new Essay();

        BeanUtils.copyProperties(essayDTO, essay);

        essay.setUpdateAt(new Timestamp(new Date().getTime()));

        User user = TokenUtils.getUser();

        essay.setUpdateBy(user == null ? null : user.getMobilePhone());

        essayDao.updateBy(essay);

        Content content = new Content(essay.getId(), essayDTO.getContent());

        return contentDao.updateBy(content) > 0;

    }

    @Override
    public boolean deletes(Long[] ids) {

        essayDao.deleteByIds(ids);

        return contentDao.deleteByIds(ids) > 0;

    }

    @Override
    public PageInfo<Essay> findPage(EssayDTO essayDTO, Pager pager) {

        Essay essay = new Essay();
        essay.setStatus(essayDTO.getStatus());
        essay.setMainTitle(essayDTO.getMainTitle());

        PageInfo<Essay> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(essayDao.findList(essay));

    }

    @Override
    public boolean updateStatus(Long[] ids, EssayConstant.Status status) {

        Essay essay = new Essay();

        Timestamp timestamp = new Timestamp(new Date().getTime());

        switch (status) {

            case DOWN:
                essay.setDownDate(timestamp);
                break;

            case PUBLISH:
                essay.setPublishDate(timestamp);
                break;

        }

        essay.setUpdateAt(timestamp);

        essay.setIds(ids);

        essay.setStatus(status.getValue());

        User user = TokenUtils.getUser();

        essay.setUpdateBy(user == null ? null : user.getMobilePhone());

        return essayDao.updateByIds(essay) > 0;

    }

    @Override
    public boolean updateQueueNum(Long[] ids, Integer queueNum) {

        Essay essay = new Essay();

        essay.setUpdateAt(new Timestamp(new Date().getTime()));

        essay.setIds(ids);

        essay.setQueueNum(queueNum);

        User user = TokenUtils.getUser();

        essay.setUpdateBy(user == null ? null : user.getMobilePhone());

        return essayDao.updateByIds(essay) > 0;

    }

}

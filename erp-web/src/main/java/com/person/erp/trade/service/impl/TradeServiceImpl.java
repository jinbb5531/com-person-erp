package com.person.erp.trade.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import com.person.erp.trade.dao.ITradeDao;
import com.person.erp.trade.entity.ProvideTrade;
import com.person.erp.trade.model.ProvideTradeDTO;
import com.person.erp.trade.service.ITradeService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>TradeServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:48
 */
@Service
@Transactional
public class TradeServiceImpl implements ITradeService {

    @Resource
    private ITradeDao tradeDao;

    @Override
    public Long add(ProvideTradeDTO dto) {

        ProvideTrade provideTrade = new ProvideTrade();

        BeanUtils.copyProperties(dto, provideTrade);


        User operator = TokenUtils.getUser();

        String createBy = null;

        Long systemTag = WebConstant.SUPER_MANAGER_TAG;

        if (operator != null) {

            createBy = operator.getUserCode();
            systemTag = operator.getSystemTag();

        }

        provideTrade.setId(null);
        provideTrade.setCreateAt(new Timestamp(new Date().getTime()));
        provideTrade.setCreateBy(createBy);
        provideTrade.setSystemTag(systemTag);

        tradeDao.insert(provideTrade);

        return provideTrade.getId();
    }

    @Override
    public ProvideTradeDTO getById(Long id) {

        ProvideTrade provideTrade = tradeDao.findById(id);

        if (provideTrade == null) {
            return null;
        }

        ProvideTradeDTO dto = new ProvideTradeDTO();

        BeanUtils.copyProperties(provideTrade, dto);

        return dto;
    }

    @Override
    public boolean update(ProvideTradeDTO dto) {

        ProvideTrade provideTrade = tradeDao.findById(dto.getId());

        if (provideTrade == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "供应商已不存在，无法修改");
        }

        ProvideTrade trade = new ProvideTrade();

        BeanUtils.copyProperties(dto, trade);

        User user = TokenUtils.getUser();

        trade.setUpdateBy(user == null ? null : user.getUserCode());

        trade.setUpdateAt(new Timestamp(new Date().getTime()));

        return tradeDao.updateBy(trade) > 0;
    }

    @Override
    public PageInfo<ProvideTrade> findPage(ProvideTradeDTO dto, Pager pager) {

        ProvideTrade provideTrade = new ProvideTrade();

        BeanUtils.copyProperties(dto, provideTrade);

        PageInfo<ProvideTrade> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(tradeDao.findList(provideTrade));
    }

    @Override
    public boolean deletes(Long[] ids) {
        return tradeDao.deleteByIds(ids) > 0;
    }
}

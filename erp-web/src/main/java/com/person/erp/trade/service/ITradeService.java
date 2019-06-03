package com.person.erp.trade.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.trade.entity.ProvideTrade;
import com.person.erp.trade.model.ProvideTradeDTO;

/**
 * <p>ITradeService.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:48
 */
public interface ITradeService {
    Long add(ProvideTradeDTO dto);

    ProvideTradeDTO getById(Long id);

    boolean update(ProvideTradeDTO dto);

    PageInfo<ProvideTrade> findPage(ProvideTradeDTO dto, Pager pager);

    boolean deletes(Long[] ids);
}

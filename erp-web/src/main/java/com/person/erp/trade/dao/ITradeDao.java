package com.person.erp.trade.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.trade.entity.ProvideTrade;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * <p>ITradeDao.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:50
 */
@MyMapper
public interface ITradeDao extends BaseDao<ProvideTrade> {

    @Override
    @Options(useGeneratedKeys = true)
    long insert(ProvideTrade entity);

    List<ProvideTrade> findList(ProvideTrade provideTrade);
}

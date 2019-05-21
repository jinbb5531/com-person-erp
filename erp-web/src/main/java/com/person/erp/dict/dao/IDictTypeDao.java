package com.person.erp.dict.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.dict.entity.DictType;
import org.apache.ibatis.annotations.Options;

/**
 * <p>IDictTypeDao.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:25
 */
@MyMapper
public interface IDictTypeDao extends BaseDao<DictType> {

    @Override
    @Options(useGeneratedKeys = true)
    long insert(DictType entity);
}

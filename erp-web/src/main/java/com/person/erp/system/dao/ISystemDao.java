package com.person.erp.system.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.system.entity.ChildSystem;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * <p>ISystemDao.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:48
 */
@MyMapper
public interface ISystemDao extends BaseDao<ChildSystem> {

    @Override
    @Options(useGeneratedKeys = true)
    long insert(ChildSystem entity);

    List<ChildSystem> findList(ChildSystem childSystem);
}

package com.person.erp.dict.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.dict.entity.Dict;
import com.person.erp.dict.model.DictDTO;
import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * <p>IDictDao.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:25
 */
@MyMapper
public interface IDictDao extends BaseDao<Dict> {

    /**
     * 关联查询字典、类型
     * @author zhuwj
     * @since 2019/5/21 14:30
     *
     * @param dict
     * @return java.util.List<com.person.erp.dict.entity.Dict>
     */
    List<Dict> findListAssociateType(Dict dict);

}

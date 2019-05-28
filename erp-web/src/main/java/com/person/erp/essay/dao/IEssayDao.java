package com.person.erp.essay.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.essay.entity.Essay;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>IEssayDao.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:50
 */
@MyMapper
public interface IEssayDao extends BaseDao<Essay> {

    @Override
    @Options(useGeneratedKeys = true)
    long insert(Essay entity);

    /**
     * 按条件查询文章
     *
     * @author zhuwj
     * @since 2019/5/27 21:38
     * @param essay
     * @return java.util.List<com.person.erp.essay.entity.Essay>
     */
    List<Essay> findList(Essay essay);

    /**
     * 批量更新
     * @author zhuwj
     * @since 2019/5/27 23:28
     * @param essay
     * @return long
     */
    long updateByIds(Essay essay);
}

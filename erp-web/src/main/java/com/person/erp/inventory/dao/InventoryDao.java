package com.person.erp.inventory.dao;

import com.itexplore.core.mybatis.MyMapper;
import com.itexplore.core.orm.BaseDao;
import com.person.erp.inventory.entity.Inventory;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>InventoryDao.java</p>
 *
 * @author zhuwj
 * @since 2019/7/23 0:12
 */
@MyMapper
public interface InventoryDao extends BaseDao<Inventory> {
    @Override
    @Options(useGeneratedKeys = true)
    long insert(Inventory entity);

    List<Inventory> findList(Inventory inventory);

}

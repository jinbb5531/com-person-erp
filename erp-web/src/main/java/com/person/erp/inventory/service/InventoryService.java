package com.person.erp.inventory.service;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.person.erp.inventory.entity.Inventory;

/**
 * <p>InventoryService.java</p>
 *
 * @author zhuwj
 * @since 2019/7/23 0:15
 */
public interface InventoryService {
    Long insert(Inventory inventory);

    Inventory get(Long id);

    boolean update(Inventory inventory);

    PageInfo<Inventory> findPage(Inventory inventory, Pager pager);

    boolean deletes(Long[] ids);

    boolean updateAmount(Long id, String amount);

    boolean updateDelivery(Long id, Integer deliveryStatus);
}


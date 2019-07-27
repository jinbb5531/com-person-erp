package com.person.erp.inventory.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.common.constant.WebConstant;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import com.person.erp.inventory.dao.InventoryDao;
import com.person.erp.inventory.entity.Inventory;
import com.person.erp.inventory.service.InventoryService;
import jdk.nashorn.internal.parser.Token;
import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>InventoryServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/7/23 0:15
 */
@Service
@Transactional
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryDao dao;

    @Override
    public Long insert(Inventory inventory) {

        inventory.setCreateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        if (operator != null) {
            inventory.setCreateBy(TokenUtils.getUser().getUserCode());
            inventory.setSystemTag(operator.getSystemTag());
        } else {
            inventory.setSystemTag(WebConstant.SUPER_MANAGER_TAG);
        }

        long insert = dao.insert(inventory);

        return insert > 0 ? inventory.getId() : null;

    }

    @Override
    @Transactional(readOnly = true)
    public Inventory get(Long id) {
        return dao.findById(id);
    }

    @Override
    public boolean update(Inventory inventory) {

        Inventory entity = dao.findById(inventory.getId());

        if (entity == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "该数据不存在，无法修改");
        }

        inventory.setUpdateAt(new Timestamp(new Date().getTime()));

        User operator = TokenUtils.getUser();

        if (operator != null) {
            inventory.setUpdateBy(operator.getUserCode());
        }

        inventory.setSystemTag(null);

        return dao.updateBy(inventory) > 0;

    }

    @Override
    @Transactional(readOnly = true)
    public PageInfo<Inventory> findPage(Inventory inventory, Pager pager) {

        PageInfo<Inventory> pageInfo = PageChangeUtils.dealPageInfo(PageChangeUtils.pagerToPageInfo(pager));

        PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());

        return new PageInfo<>(dao.findList(inventory));

    }

    @Override
    public boolean deletes(Long[] ids) {

        return dao.deleteByIds(ids) > 0;

    }

    @Override
    public boolean updateAmount(Long id, String amount) {

        if (dao.findById(id) == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "该数据不存在，无法修改");
        }

        Inventory inventory = new Inventory();

        inventory.setId(id);

        inventory.setAmount(amount);

        return dao.updateBy(inventory) > 0;

    }

    @Override
    public boolean updateDelivery(Long id, Integer deliveryStatus) {

        if (dao.findById(id) == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "该数据不存在，无法修改");
        }

        Inventory inventory = new Inventory();

        inventory.setId(id);

        inventory.setDeliveryStatus(deliveryStatus);

        return dao.updateBy(inventory) > 0;

    }

}

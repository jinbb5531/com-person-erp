package com.person.erp.inventory.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.model.ParamDTO;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Single;
import com.person.erp.common.valid.Update;
import com.person.erp.common.valid.UpdatePwd;
import com.person.erp.inventory.entity.Inventory;
import com.person.erp.inventory.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;

/**
 * <p>InventoryController.java</p>
 *
 * @author zhuwj
 * @since 2019/7/23 0:14
 */
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Resource
    private InventoryService inventoryService;

    @PostMapping("/add")
    @Permission(modelName = "库存管理", name = "新增产品")
    public ResponseEntity add(@RequestBody @Validated Inventory inventory) {

        Long id = inventoryService.insert(inventory);

        return ResultUtils.success("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        Inventory inventory = inventoryService.get(id);

        return ResultUtils.success(inventory);

    }

    @PutMapping("/update")
    @Permission(modelName = "库存管理", name = "修改产品")
    public ResponseEntity update(@RequestBody @Validated({Default.class, Update.class}) Inventory inventory) {

        boolean success = inventoryService.update(inventory);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/page")
    @Permission(modelName = "库存管理", name = "查看库存列表")
    public ResponseEntity findPage(Inventory inventory, Pager pager) {

        PageInfo<Inventory> pageInfo = inventoryService.findPage(inventory, pager);

        return ResultUtils.asserts(new PageResult<>(pageInfo.getList(), PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @DeleteMapping("/deletes")
    @Permission(modelName = "库存管理", name = "删除产品")
    public ResponseEntity deletes(@RequestBody @Validated(Delete.class) ParamDTO dto) {

        boolean success = inventoryService.deletes(dto.getIds());

        return ResultUtils.asserts(success);

    }

    @PutMapping("/amount")
    public ResponseEntity updateAmount(@RequestBody @Validated(Single.class) Inventory inventory) {

        boolean success = inventoryService.updateAmount(inventory.getId(), inventory.getAmount());

        return ResultUtils.asserts(success);

    }

    @PutMapping("/delivery")
    public ResponseEntity updateDelivery(@RequestBody @Validated(UpdatePwd.class) Inventory inventory) {

        boolean success = inventoryService.updateDelivery(inventory.getId(), inventory.getDeliveryStatus());

        return ResultUtils.asserts(success);

    }
}

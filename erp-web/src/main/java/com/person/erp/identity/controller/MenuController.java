package com.person.erp.identity.controller;

import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.utils.DealResultUtils;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.service.IMenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>MenuController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/22 9:13
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @PostMapping("/add")
    public ResponseEntity addMenu(@RequestBody @Validated MenuDTO menuDTO) {

        Long id = menuService.addMenu(menuDTO);

        return DealResultUtils.dealData("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getMenu(@PathVariable("id") Long id) {

        MenuDTO menuDTO = menuService.getMenuById(id);

        return ResultUtils.asserts(menuDTO);

    }

    @PutMapping("/update")
    public ResponseEntity updateMenu(@RequestBody @Validated({Update.class}) MenuDTO menuDTO) {

        boolean success = menuService.updateMenu(menuDTO);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/tree")
    public ResponseEntity findMenuTree() {

        List<MenuDTO> treeList = menuService.findMenuTree();

        return ResultUtils.asserts(treeList);

    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(@RequestBody @Validated({Delete.class}) MenuDTO menuDTO) {

        boolean success = menuService.delete(menuDTO.getId());

        return ResultUtils.asserts(success);

    }

}

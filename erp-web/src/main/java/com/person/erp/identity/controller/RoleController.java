package com.person.erp.identity.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.utils.PojoChangeUtils;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>RoleController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/17 12:47
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    @Permission(modelName = "角色管理", name = "添加角色")
    public ResponseEntity addRole(@RequestBody @Validated RoleDTO roleDTO) {

        long id = roleService.addRole(roleDTO);

        if (id > 0) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", id);
            return ResultUtils.asserts(data);
        } else {
            return ResultUtils.failure();
        }

    }

    @PutMapping("/update")
    @Permission(modelName = "角色管理", name = "修改角色")
    public ResponseEntity updateRole(@RequestBody @Validated({Update.class}) RoleDTO roleDTO) {

        boolean success = roleService.updateRole(roleDTO);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable Long id) {

        RoleDTO roleDTO = roleService.getRoleById(id);

        return ResultUtils.asserts(roleDTO);

    }

    @GetMapping("/list")
    public ResponseEntity findList() {

        List<RoleDTO> list = roleService.findAll();

        return ResultUtils.asserts(list);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(RoleDTO roleDTO, Pager pager) {

        PageInfo<Role> rolePageInfo = roleService.findPage(roleDTO.getRoleName(), pager);

        List<Role> list = rolePageInfo.getList();

        List<RoleDTO> roleDTOList = new ArrayList<>(list.size());

        PojoChangeUtils.copyList(list, roleDTOList, RoleDTO.class);

        return ResultUtils.asserts(new PageResult<>(roleDTOList, PageChangeUtils.pageInfoToPager(rolePageInfo)));

    }

    @DeleteMapping("/deletes")
    @Permission(modelName = "角色管理", name = "删除角色")
    public ResponseEntity deleteBatch(@RequestBody @Validated({Delete.class}) RoleDTO roleDTO) {

        boolean success = roleService.deleteBatch(roleDTO.getIds());

        return ResultUtils.asserts(success);

    }

}

package com.person.erp.system.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.system.entity.ChildSystem;
import com.person.erp.system.model.ChildSystemDTO;
import com.person.erp.system.service.ISystemService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>SystemController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/30 23:18
 */
@RestController
@RequestMapping("/api/sys")
public class SystemController {

    @Resource
    private ISystemService systemService;

    @PostMapping("/add")
    @Permission(modelName = "子系统管理", name = "新增子系统")
    public ResponseEntity add(@RequestBody @Validated ChildSystemDTO dto) {

        Long id = systemService.add(dto);

        return ResultUtils.success("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        ChildSystem childSystem = systemService.getById(id);

        return ResultUtils.success(childSystem);

    }

    @PutMapping("/update")
    @Permission(modelName = "子系统管理", name = "修改子系统")
    public ResponseEntity update(@RequestBody @Validated(Update.class) ChildSystemDTO dto) {

        boolean success = systemService.update(dto);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(ChildSystemDTO dto, Pager pager) {

        PageInfo<ChildSystem> pageInfo = systemService.findPage(dto, pager);

        return ResultUtils.asserts(PageChangeUtils.pageInfoToPageResult(pageInfo));

    }

    @DeleteMapping("/delete")
    @Permission(modelName = "子系统管理", name = "删除子系统")
    public ResponseEntity delete(@RequestBody @Validated(Delete.class) ChildSystemDTO dto) {

        boolean success = systemService.delete(dto.getId());

        return ResultUtils.asserts(success);

    }

    @GetMapping("/list")
    public ResponseEntity findAll() {

        List<ChildSystem> list = systemService.findList(null);

        return ResultUtils.success(list);

    }

}

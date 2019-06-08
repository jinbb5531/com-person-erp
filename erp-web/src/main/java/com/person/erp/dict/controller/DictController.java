package com.person.erp.dict.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.utils.DealResultUtils;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Single;
import com.person.erp.common.valid.Update;
import com.person.erp.dict.entity.Dict;
import com.person.erp.dict.entity.DictType;
import com.person.erp.dict.model.DictDTO;
import com.person.erp.dict.service.IDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>DictController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/20 16:13
 */
@RestController
@RequestMapping("/api/dict")
public class DictController {

    @Resource
    private IDictService dictService;

    @GetMapping("/type/check")
    public ResponseEntity checkType(@Validated DictType dictType) {

        boolean exist = dictService.existType(dictType.getTypeName());

        Map<String, Object> data = new HashMap<>();
        data.put("exist", exist);

        return ResultUtils.asserts(data);

    }

    @PostMapping("/type/add")
    public ResponseEntity addType(@RequestBody @Validated DictType dictType) {

        Long id = dictService.addType(dictType.getTypeName());

        return ResultUtils.asserts(id != null);

    }

    @GetMapping("/type/list")
    public ResponseEntity findAllType() {

        List<DictType> list = dictService.findAllType();

        List<String> typeList = new ArrayList<>();
        list.forEach(dictType -> typeList.add(dictType.getTypeName()));

        return ResultUtils.asserts(typeList);

    }

    @GetMapping("/check")
    public ResponseEntity checkDict(@Validated(Single.class) DictDTO dictDTO) {

        DictDTO dto = dictService.getDict(dictDTO.getId());

        return ResultUtils.success("exist", dto != null);

    }

    @PostMapping("/add")
    @Permission(modelName = "字典管理", name = "新增字典")
    public ResponseEntity addDict(@RequestBody @Validated DictDTO dictDTO) {

        String id = dictService.addDict(dictDTO);

        return DealResultUtils.dealData("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getDict(@PathVariable("id") String id) {

        DictDTO dictDTO = dictService.getDict(id);

        return ResultUtils.asserts(dictDTO);

    }

    @PutMapping("/update")
    @Permission(modelName = "字典管理", name = "修改字典")
    public ResponseEntity updateDict(@RequestBody @Validated({Update.class}) DictDTO dictDTO) {

        boolean success = dictService.updateDict(dictDTO);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/find")
    public ResponseEntity findDictList(DictDTO dictDTO) {

        List<DictDTO> dictList = dictService.findDictList(dictDTO);

        return ResultUtils.asserts(dictList);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(DictDTO dictDTO, Pager pager) {

        PageInfo<Dict> pageInfo = dictService.findPage(dictDTO, pager);

        List<DictDTO> list = dictService.dealDictChange(pageInfo.getList());

        return ResultUtils.asserts(new PageResult<>(list, PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @DeleteMapping("/deletes")
    @Permission(modelName = "字典管理", name = "删除字典")
    public ResponseEntity deletes(@RequestBody @Validated(Delete.class) DictDTO dictDTO) {

        boolean success = dictService.deletes(dictDTO.getIds());

        return ResultUtils.asserts(success);

    }


}

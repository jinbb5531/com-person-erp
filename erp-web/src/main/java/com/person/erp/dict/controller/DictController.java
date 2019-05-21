package com.person.erp.dict.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.utils.DealResultUtils;
import com.person.erp.common.valid.Delete;
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

        return DealResultUtils.dealData("id", id);

    }

    @GetMapping("/type/list")
    public ResponseEntity findAllType() {

        List<DictType> list = dictService.findAllType();

        return ResultUtils.asserts(list);

    }

    @GetMapping("/type/find")
    public ResponseEntity findTypeByName(DictType dictType) {

        dictType = dictService.findTypeByName(dictType.getTypeName());

        return ResultUtils.asserts(dictType == null ? Collections.EMPTY_MAP : dictType);

    }

    @GetMapping("/type/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        DictType dictType = dictService.findTypeById(id);

        return ResultUtils.asserts(dictType);

    }

    @PostMapping("/add")
    public ResponseEntity addDict(@RequestBody @Validated DictDTO dictDTO) {

        Long id = dictService.addDict(dictDTO);

        return DealResultUtils.dealData("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getDict(@PathVariable("id") Long id) {

        DictDTO dictDTO = dictService.getDict(id);

        return ResultUtils.asserts(dictDTO);

    }

    @PutMapping("/update")
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

        List<DictDTO> list = new ArrayList<>();

        pageInfo.getList().forEach( dict -> {

            DictDTO dto = new DictDTO();

            BeanUtils.copyProperties(dict, dto);

            dto.setTypeName(dict.getDictType().getTypeName());

            list.add(dto);

        });

        return ResultUtils.asserts(new PageResult<>(list, PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @DeleteMapping("/deletes")
    public ResponseEntity deletes(@RequestBody @Validated(Delete.class) DictDTO dictDTO) {

        boolean success = dictService.deletes(dictDTO.getIds());

        return ResultUtils.asserts(success);

    }


}
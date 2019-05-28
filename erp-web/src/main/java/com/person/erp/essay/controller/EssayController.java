package com.person.erp.essay.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.essay.constant.EssayConstant;
import com.person.erp.essay.entity.Essay;
import com.person.erp.essay.model.EssayDTO;
import com.person.erp.essay.service.IEssayService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>EssayController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/27 19:11
 */
@RestController
@RequestMapping("/api/essay")
public class EssayController {

    @Resource
    private IEssayService essayService;

    @PostMapping("/add")
    @Permission(modelName = "文章管理", name = "新建文章")
    public ResponseEntity add(@RequestBody @Validated EssayDTO essayDTO) {

        Long id = essayService.add(essayDTO);

        return ResultUtils.success("id", id);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        EssayDTO essayDTO = essayService.getById(id);

        return ResultUtils.success(essayDTO);

    }

    @PutMapping("/update")
    @Permission(modelName = "文章管理", name = "修改文章")
    public ResponseEntity update(@RequestBody @Validated({Update.class})EssayDTO essayDTO) {

        essayDTO.setStatus(null);
        essayDTO.setQueueNum(null);

        boolean success = essayService.update(essayDTO);

        return ResultUtils.asserts(success);

    }

    @DeleteMapping("/deletes")
    @Permission(modelName = "文章管理", name = "删除文章")
    public ResponseEntity deletes(@RequestBody @Validated({Delete.class})EssayDTO essayDTO) {

        boolean success = essayService.deletes(essayDTO.getIds());

        return ResultUtils.asserts(success);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(EssayDTO essayDTO, Pager pager) {

        PageInfo<Essay> pageInfo = essayService.findPage(essayDTO, pager);

        return ResultUtils.asserts(PageChangeUtils.pageInfoToPageResult(pageInfo));
    }

    @PutMapping("/down")
    public ResponseEntity downEssay(@RequestBody @Validated({Delete.class})EssayDTO essayDTO) {

        boolean success = essayService.updateStatus(essayDTO.getIds(), EssayConstant.Status.DOWN);

        return ResultUtils.asserts(success);

    }

    @PutMapping("/publish")
    public ResponseEntity publishEssay(@RequestBody @Validated({Delete.class})EssayDTO essayDTO) {

        boolean success = essayService.updateStatus(essayDTO.getIds(), EssayConstant.Status.PUBLISH);

        return ResultUtils.asserts(success);

    }

    @PutMapping("/top")
    public ResponseEntity topEssay(@RequestBody @Validated({Delete.class})EssayDTO essayDTO) {

        boolean success = essayService.updateQueueNum(essayDTO.getIds(), EssayConstant.TOP);

        return ResultUtils.asserts(success);

    }

    @PutMapping("/top/cancel")
    public ResponseEntity cancelTopEssay(@RequestBody @Validated({Delete.class})EssayDTO essayDTO) {

        boolean success = essayService.updateQueueNum(essayDTO.getIds(), EssayConstant.NORMAL);

        return ResultUtils.asserts(success);

    }


}

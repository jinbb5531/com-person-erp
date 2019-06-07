package com.person.erp.trade.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.utils.PojoChangeUtils;
import com.person.erp.common.valid.Delete;
import com.person.erp.common.valid.Update;
import com.person.erp.trade.entity.ProvideTrade;
import com.person.erp.trade.model.ProvideTradeDTO;
import com.person.erp.trade.service.ITradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>TradeController.java</p>
 *
 * @author zhuwj
 * @since 2019/6/3 18:37
 */
@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Resource
    private ITradeService tradeService;

    @PostMapping("/add")
    public ResponseEntity add(@RequestBody @Validated ProvideTradeDTO dto) {

        Long id = tradeService.add(dto);

        return ResultUtils.success("id", id);

    }

    @GetMapping("/get/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        ProvideTradeDTO dto = tradeService.getById(id);

        return ResultUtils.success(dto);

    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody @Validated({Update.class}) ProvideTradeDTO dto) {

        boolean success = tradeService.update(dto);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(ProvideTradeDTO dto, Pager pager) {

        PageInfo<ProvideTrade> pageInfo = tradeService.findPage(dto, pager);

        List<ProvideTradeDTO> list = new ArrayList<>();

        PojoChangeUtils.copyList(pageInfo.getList(), list, ProvideTradeDTO.class);

        return ResultUtils.asserts(new PageResult<>(list, PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @DeleteMapping("/deletes")
    public ResponseEntity deletes(@RequestBody @Validated({Delete.class}) ProvideTradeDTO dto) {

        boolean success = tradeService.deletes(dto.getIds());

        return ResultUtils.asserts(success);

    }

}

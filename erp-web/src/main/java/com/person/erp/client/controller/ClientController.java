package com.person.erp.client.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.client.entity.Client;
import com.person.erp.client.service.IClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Resource
    private IClientService clientService;

    /**
     * 添加客户
     */
    @PostMapping
    private ResponseEntity create (@Validated Client client){
        boolean success = clientService.insert(client);
        if (success){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("操作失败");
        }
    }

    /**
     * 更新
     * @param client
     * @return
     */
    @PutMapping
    private ResponseEntity update(@Validated Client client){
        boolean success = clientService.update(client);
        if (success){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("操作失败");
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @DeleteMapping
    private ResponseEntity delete(String ids){
        boolean success = clientService.deleteBatch(ids);
        if (success){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("操作失败");
        }
    }

    /**
     * 查询单条
     * @param client
     * @return
     */
    @GetMapping
    private ResponseEntity get(Client client){
        Client res = clientService.get(client);
        if (client != null){
            return ResultUtils.success(res);
        }else{
            return ResultUtils.success();
        }
    }

    /**
     * 条件分页查询
     * @param client
     * @param pageInfo
     * @return
     */
    @GetMapping("/page")
    private ResponseEntity findPage(Client client, PageInfo<Client> pageInfo){
        PageInfo<Client> page = clientService.findPage(pageInfo, client);
        return ResultUtils.success(PageChangeUtils.pageInfoToPageResult(page));
    }
}

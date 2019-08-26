package com.person.erp.client.controller;

import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.client.entity.Client;
import com.person.erp.client.service.IClientService;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity create (Client client){
        boolean success = clientService.insert(client);
        if (success){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("操作失败");
        }
    }

    @PutMapping
    private ResponseEntity update(Client client){
        boolean success = clientService.update(client);
        if (success){
            return ResultUtils.success();
        }else{
            return ResultUtils.failure("操作失败");
        }
    }

    @GetMapping
    private ResponseEntity get(Client client){
        Client res = clientService.get(client);
        if (client != null){
            return ResultUtils.success(res);
        }else{
            return ResultUtils.failure("");
        }
    }
}

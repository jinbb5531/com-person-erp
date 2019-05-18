package com.person.erp.identity.controller;

import com.person.erp.common.valid.Update;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>RoleController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/17 12:47
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    public Object addRole(@RequestBody @Validated RoleDTO roleDTO) {

        long id = roleService.addRole(roleDTO);

        if (id > 0) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("id", id);
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PutMapping("/update")
    public Object updateRole(@RequestBody @Validated({Update.class}) RoleDTO roleDTO) {


        return null;
    }

}

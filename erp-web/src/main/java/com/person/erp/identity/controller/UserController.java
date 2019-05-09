package com.person.erp.identity.controller;

import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.UserDTO;
import com.person.erp.identity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>UserController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/5 16:10
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public Object add(@RequestBody @Valid UserDTO userDTO) {

        boolean success = userService.addUser(userDTO);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest();
        }

    }

    @GetMapping("/get/{code}/{tag}")
    public Object get(@PathVariable("code") String code, @PathVariable("tag") long systemTag) {

        User user = userService.getUser(code, systemTag);

        return ResponseEntity.ok().body(user);

    }

    @PostMapping("/update")
    public Object update(@RequestBody UserDTO userDTO) {

        boolean success = userService.updateUser(userDTO);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest();
        }

    }

}

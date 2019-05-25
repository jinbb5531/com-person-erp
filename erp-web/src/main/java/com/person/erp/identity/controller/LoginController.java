package com.person.erp.identity.controller;

import com.itexplore.core.api.utils.ResultUtils;
import com.person.erp.common.utils.DealResultUtils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.common.valid.Login;
import com.person.erp.common.valid.LoginPhone;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.model.UserDTO;
import com.person.erp.identity.service.ILoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>LoginController.java</p>
 *
 * @author zhuwj
 * @since 2019/5/23 12:38
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Resource
    private ILoginService loginService;

    @PostMapping("/into")
    public ResponseEntity loginInto(@RequestBody @Validated({Login.class}) UserDTO userDTO) {

        String token = loginService.loginInto(userDTO.getUserCode(), userDTO.getSystemTag(), userDTO.getUserPwd());

        return DealResultUtils.dealData("token", token);

    }

    @PostMapping("/phone")
    public ResponseEntity loginIntoByPhone(@RequestBody @Validated({LoginPhone.class}) UserDTO userDTO) {

        String token = loginService.loginIntoByPhone(userDTO.getMobilePhone(), userDTO.getUserPwd());

        return DealResultUtils.dealData("token", token);

    }

    @PutMapping("/out")
    public ResponseEntity loginOut() {

        User user = TokenUtils.getUser();

        TokenUtils.removeUser(user);

        TokenUtils.removePermission();

        return ResultUtils.asserts(user != null);
    }

    @GetMapping("/user")
    public ResponseEntity getLoginUser() {

        User user = TokenUtils.getUser();

        return ResultUtils.asserts(user);

    }

    @GetMapping("/permission")
    public ResponseEntity getLoginUserPermission() {

        List<MenuDTO> permission = TokenUtils.getPermission();

        return ResultUtils.asserts(permission);
    }

}

package com.person.erp.identity.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.model.UserDTO;
import com.person.erp.identity.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public Object add(@RequestBody @Validated UserDTO userDTO) {

        boolean success = userService.addUser(userDTO);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/get/{code}/{tag}")
    public Object get(@PathVariable("code") String code, @PathVariable("tag") long systemTag) {

        User user = userService.getUser(code, systemTag);

        return ResponseEntity.ok().body(user);

    }

    @PutMapping("/update")
    public Object update(@RequestBody @Validated UserDTO userDTO) {

        boolean success = userService.updateUser(userDTO);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DeleteMapping("/deletes")
    public Object deleteBatch(@RequestBody List<UserDTO> userList) {

        for (UserDTO userDTO : userList) {

            if (JudgeUtils.isEmpty(userDTO.getUserCode())) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "元素中的userCode 不能为空");
            }

        }

        boolean success = userService.deleteBatch(userList);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping("/page")
    public PageResult findPage(UserDTO userDTO, Pager pager) {

        PageInfo<User> pageInfo = userService.findPage(userDTO, pager);

        List<UserDTO> dtoList = new ArrayList<>(pageInfo.getList().size());

        pageInfo.getList().forEach(user -> {

            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);

            List<Role> roleList = user.getRoleList();
            List<RoleDTO> roleDTOList = new ArrayList<>();

            if (roleList != null) {

                roleList.forEach(role -> {

                    RoleDTO roleDTO = new RoleDTO();
                    BeanUtils.copyProperties(role, roleDTO);
                    roleDTOList.add(roleDTO);

                });

            }

            dto.setRoles(roleDTOList);

            dtoList.add(dto);

        });

        return new PageResult(dtoList, PageChangeUtils.pageInfoToPager(pageInfo));

    }

}

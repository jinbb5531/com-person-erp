package com.person.erp.identity.controller;

import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.model.ApiException;
import com.itexplore.core.api.model.PageResult;
import com.itexplore.core.api.model.Pager;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.itexplore.core.api.utils.ResultUtils;
import com.itexplore.core.common.utils.judge.JudgeUtils;
import com.person.erp.common.annotation.Permission;
import com.person.erp.common.utils.PojoChangeUtils;
import com.person.erp.common.valid.*;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.ListDTO;
import com.person.erp.identity.model.LoginDTO;
import com.person.erp.identity.model.RoleDTO;
import com.person.erp.identity.model.UserDTO;
import com.person.erp.identity.service.IUserService;
import javax.validation.constraints.NotEmpty;

import com.person.erp.order.entity.OrderOperate;
import com.person.erp.order.service.IOrderOperateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    private IOrderOperateService orderOperateService;

    @PostMapping("/add")
    @Permission(modelName = "用户管理", name = "添加用户")
    public ResponseEntity add(@RequestBody @Validated({Insert.class}) UserDTO userDTO) {

        boolean success = userService.addUser(userDTO);

        return ResultUtils.asserts(success);

    }

    @GetMapping("/get/{code}/{tag}")
    public ResponseEntity get(@PathVariable("code") String code, @PathVariable("tag") long systemTag) {

        User user = userService.getUser(code, systemTag);

        return ResultUtils.asserts(user);

    }

    @PutMapping("/update")
    @Permission(modelName = "用户管理", name = "修改用户")
    public ResponseEntity update(@RequestBody @Validated({Update.class}) UserDTO userDTO) {

        boolean success = userService.updateUser(userDTO);

        return ResultUtils.asserts(success);

    }

    @DeleteMapping("/deletes")
    @Permission(modelName = "用户管理", name = "删除用户")
    public ResponseEntity deleteBatch(@RequestBody @Validated({UserDelete.class, Delete.class}) ListDTO listDTO) {

//        for (UserDTO userDTO : userList) {
//
//            if (JudgeUtils.isEmpty(userDTO.getUserCode())) {
//                throw new ApiException(HttpStatus.BAD_REQUEST, "元素中的userCode 不能为空");
//            }
//
//        }

        boolean success = userService.deleteBatch(listDTO.getUsers());

        return ResultUtils.asserts(success);

    }

    @GetMapping("/page")
    public ResponseEntity findPage(UserDTO userDTO, Pager pager) {

        PageInfo<User> pageInfo = userService.findPage(userDTO, pager);

        List<UserDTO> dtoList = new ArrayList<>(pageInfo.getList().size());

        pageInfo.getList().forEach(user -> {

            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);

            List<Role> roleList = user.getRoleList();
            List<RoleDTO> roleDTOList = new ArrayList<>();

            PojoChangeUtils.copyList(roleList, roleDTOList, RoleDTO.class);

            dto.setRoles(roleDTOList);

            dtoList.add(dto);

        });


        return ResultUtils.asserts(new PageResult<>(dtoList, PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @GetMapping("/salary/page")
    @Permission(modelName = "工资管理", name = "查询工资列表")
    public ResponseEntity findPageSalary(UserDTO userDTO, Pager pager) {

        PageInfo<User> pageInfo = userService.findPage(userDTO, pager);

        List<UserDTO> dtoList = new ArrayList<>(pageInfo.getList().size());

        pageInfo.getList().forEach(user -> {

            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(user, dto);

            List<Role> roleList = user.getRoleList();
            List<RoleDTO> roleDTOList = new ArrayList<>();

            PojoChangeUtils.copyList(roleList, roleDTOList, RoleDTO.class);

            dto.setRoles(roleDTOList);

            dtoList.add(dto);

        });

        // 统计出其总件数
        List<OrderOperate> orderOperateList = orderOperateService.sumCutNumGroupByUserList(dtoList, userDTO.getStartTime(), userDTO.getEndTime());

        dtoList.forEach(dto -> orderOperateList.forEach(orderOperate -> {
            if (Objects.equals(dto.getSystemTag(), orderOperate.getSystemTag())
                    && Objects.equals(dto.getUserCode(), orderOperate.getOperaCode())) {
                dto.setCountNum(orderOperate.getCutNum());
            }
        }));

        return ResultUtils.asserts(new PageResult<>(dtoList, PageChangeUtils.pageInfoToPager(pageInfo)));

    }

    @PutMapping("/update/password")
    public ResponseEntity updatePassword(@RequestBody @Validated({UpdatePwd.class}) LoginDTO loginDTO) {

        boolean success = userService.updatePassword(loginDTO.getUserCode(), loginDTO.getSystemTag(), loginDTO.getUserPwd());

        return ResultUtils.asserts(success);
    }

    @PutMapping("/update/password/new")
    public ResponseEntity updatePasswordValid(@RequestBody @Validated({UpdatePwdValid.class}) LoginDTO loginDTO) {

        boolean success = userService.updatePasswordValid(loginDTO.getUserCode(), loginDTO.getSystemTag(), loginDTO.getOldPwd(), loginDTO.getNewPwd());

        return ResultUtils.asserts(success);
    }

}

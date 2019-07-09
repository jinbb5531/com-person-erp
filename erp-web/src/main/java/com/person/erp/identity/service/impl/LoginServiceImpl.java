package com.person.erp.identity.service.impl;

import com.itexplore.core.api.model.ApiException;
import com.person.erp.common.utils.MD5Utils;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.Menu;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.entity.User;
import com.person.erp.identity.model.MenuDTO;
import com.person.erp.identity.service.ILoginService;
import com.person.erp.identity.service.IMenuService;
import com.person.erp.identity.service.IRoleService;
import com.person.erp.identity.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>LoginServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/23 22:14
 */
@Service
@Transactional
public class LoginServiceImpl implements ILoginService {

    @Resource
    private IUserService userService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuService menuService;

    @Value("${token.timeout:30}")
    private int timeout;

    @Override
    public String loginInto(String userCode, Long systemTag, String userPwd) {

        User user = userService.getUser(userCode, systemTag);

        if (user == null) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "用户不存在");
        }

        if (!user.getUserPwd().equals(MD5Utils.getMD5(userPwd))) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "密码错误");
        }

        // 将用户放入Redis中
        user.setUserPwd(null);

        String token = TokenUtils.recordUser(user, timeout, TimeUnit.MINUTES);

        // 整理出其所有角色
        List<Long> roleIdList = new ArrayList<>();

        List<Role> roleList = user.getRoleList();

        List<MenuDTO> permissionList = new ArrayList<>();

        List<MenuDTO> menuList = new ArrayList<>();

        if (roleList.size() > 0) {

            roleList.forEach(role -> roleIdList.add(role.getId()));

            permissionList = menuService.getPermissionByRoleIds(roleIdList.toArray(new Long[0]));

            menuList = menuService.getMenusByRoleIds(roleIdList.toArray(new Long[0]));

        }

        // 将用户权限放入 Redis 中
        TokenUtils.recordPermission(token, permissionList, timeout, TimeUnit.MINUTES);

        // 将角色对应的所有菜单放入 redis 中
        TokenUtils.recordMenuList(token, menuList, timeout, TimeUnit.MINUTES);

        return token;
    }

    @Override
    public String loginIntoByPhone(String mobilePhone, String userPwd) {

        User user = userService.getUserByPhone(mobilePhone);

        if (user != null) {

            return loginInto(user.getUserCode(), user.getSystemTag(), userPwd);

        } else {

            throw new ApiException(HttpStatus.BAD_REQUEST, "用户不存在");

        }

    }
}

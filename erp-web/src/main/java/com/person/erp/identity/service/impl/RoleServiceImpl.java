package com.person.erp.identity.service.impl;

import com.person.erp.identity.dao.IRoleDao;
import com.person.erp.identity.entity.Role;
import com.person.erp.identity.service.IRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>RoleServiceImpl.java</p>
 *
 * @author zhuwj
 * @since 2019/5/9 17:09
 */
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;

    @Override
    public List<Role> findList(Role role) {
        return roleDao.findList(role);
    }
}

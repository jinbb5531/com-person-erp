package com.person.erp.client.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itexplore.core.api.utils.PageChangeUtils;
import com.person.erp.client.dao.IClientDAO;
import com.person.erp.client.entity.Client;
import com.person.erp.client.service.IClientService;
import com.person.erp.common.utils.TokenUtils;
import com.person.erp.identity.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
public class ClientServiceImpl implements IClientService {

    @Resource
    private IClientDAO clientDAO;

    @Override
    public boolean insert(Client client) {
        User user = TokenUtils.getUser();
        client.setCreateBy(user.getUserName());
        client.setCreateAt(new Timestamp(new Date().getTime()));
        client.setIsSettle(client.getPayable().compareTo(client.getPaid())>= 0 ? 0 : 1);
        client.setSystemTag(user.getSystemTag());
        return clientDAO.insert(client) > 0;
    }

    @Override
    public boolean delete(Client client) {
        return clientDAO.deleteBy(client) > 0;
    }

    @Override
    public boolean deleteBatch(String ids) {
        return clientDAO.deleteByIds(ids.split(",")) > 0;
    }

    @Override
    public boolean update(Client client) {
        User user = TokenUtils.getUser();
        client.setIsSettle(client.getPayable().compareTo(client.getPaid())>= 0 ? 0 : 1);
        client.setUpdateBy(user.getUserName());
        client.setSystemTag(user.getSystemTag());
        client.setUpdateAt(new Timestamp(new Date().getTime()));
        return clientDAO.update(client) > 0;
    }

    @Override
    public Client get(Client client) {
        return clientDAO.findById(client.getId());
    }

    @Override
    public PageInfo<Client> findPage(PageInfo<Client> page, Client client) {
        client.setSystemTag(TokenUtils.getUser().getSystemTag());
        //处理分页参数
        PageInfo<Client> pageInfo = PageChangeUtils.dealPageInfo(page);
        Page<Object> pageData = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        page.setList(clientDAO.findPage(client));
        page.setPageNum(pageData.getPageNum());
        page.setPageSize(pageData.getPageSize());
        page.setPages(pageData.getPages());
        page.setTotal(pageData.getTotal());
        return page;
    }
}

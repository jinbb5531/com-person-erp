package com.person.erp.client.service.impl;

import com.github.pagehelper.PageInfo;
import com.person.erp.client.dao.IClientDAO;
import com.person.erp.client.entity.Client;
import com.person.erp.client.service.IClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Resource
    private IClientDAO clientDAO;

    @Override
    public boolean insert(Client client) {

        return clientDAO.insert(client) > 0;
    }

    @Override
    public boolean delete(Client client) {
        return clientDAO.deleteBy(client) > 0;
    }

    @Override
    public boolean update(Client client) {
        return clientDAO.update(client) > 0;
    }

    @Override
    public Client get(Client client) {
        return clientDAO.findById(client.getId());
    }

    @Override
    public List<Client> findPage(PageInfo<Client> pageInfo, Client client) {
        return clientDAO.findAll();
    }
}

package com.person.erp.client.service;

import com.github.pagehelper.PageInfo;
import com.person.erp.client.entity.Client;

import java.util.List;

public interface IClientService {


    boolean insert(Client client);

    boolean delete(Client client);

    boolean deleteBatch(String ids);

    boolean update(Client client);

    Client get(Client client);

    PageInfo<Client> findPage(PageInfo<Client> pageInfo, Client client);
}

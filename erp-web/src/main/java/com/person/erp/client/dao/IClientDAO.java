package com.person.erp.client.dao;

import com.itexplore.core.orm.BaseDao;
import com.person.erp.client.entity.Client;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IClientDAO extends BaseDao<Client> {

    List<Client> findPage(Client client);
}

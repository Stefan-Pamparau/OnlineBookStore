package com.iquestgroup.service.impl;

import com.iquestgroup.database.ClientDAO;
import com.iquestgroup.model.Client;
import com.iquestgroup.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultClientService implements ClientService {
    @Autowired
    private ClientDAO clientDAO;

    @Override
    public List<Client> listAllClients() {
        return clientDAO.listAllClients();
    }

    @Override
    public void insertClient(Client client) {
        clientDAO.insertClient(client);
    }

    @Override
    public void deleteClient(Integer clientID) {
        clientDAO.deleteClient(clientID);
    }

    @Override
    public void purchaseBook(Integer clientID, Integer bookID) {
        clientDAO.purchaseBook(clientID, bookID);
    }
}

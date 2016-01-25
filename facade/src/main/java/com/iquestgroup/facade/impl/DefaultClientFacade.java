package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.ClientFacade;
import com.iquestgroup.model.Client;
import com.iquestgroup.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultClientFacade implements ClientFacade {
    @Autowired
    private ClientService clientService;

    @Override
    public List<Client> listAllClients() {
        return clientService.listAllClients();
    }

    @Override
    public void insertClient(Client client) {
        clientService.insertClient(client);
    }

    @Override
    public void deleteClient(Integer clientID) {
        clientService.deleteClient(clientID);
    }

    @Override
    public void purchaseBook(Integer clientID, Integer bookID) {
        clientService.purchaseBook(clientID, bookID);
    }
}

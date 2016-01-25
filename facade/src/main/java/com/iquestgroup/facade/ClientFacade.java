package com.iquestgroup.facade;

import com.iquestgroup.model.Client;

import java.util.List;

public interface ClientFacade {
    List<Client> listAllClients();

    void insertClient(Client client);

    void deleteClient(Integer clientID);

    void purchaseBook(Integer clientID, Integer bookID);
}

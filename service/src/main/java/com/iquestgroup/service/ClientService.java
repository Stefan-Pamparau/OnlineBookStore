package com.iquestgroup.service;

import com.iquestgroup.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> listAllClients();

    void insertClient(Client client);

    void deleteClient(Integer clientID);

    void purchaseBook(Integer clientID, Integer bookID);
}

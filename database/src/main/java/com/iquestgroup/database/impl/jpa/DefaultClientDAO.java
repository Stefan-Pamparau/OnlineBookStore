package com.iquestgroup.database.impl.jpa;

import com.iquestgroup.database.ClientDAO;
import com.iquestgroup.model.Client;

import java.util.List;

public class DefaultClientDAO implements ClientDAO {
    @Override
    public List<Client> listAllClients() {
        return null;
    }

    @Override
    public void insertClient(Client client) {

    }

    @Override
    public void deleteClient(Integer clientID) {

    }

    @Override
    public void purchaseBook(Integer clientID, Integer bookID) {

    }
}

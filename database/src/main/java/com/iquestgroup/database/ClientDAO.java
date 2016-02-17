package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Client;

import java.util.List;

public interface ClientDAO {
    List<Client> listAllClients() throws DAOException;

    String insertClient(Client client) throws DAOException;

    String deleteClient(Integer clientID) throws DAOException;

    String purchaseBook(Integer clientID, Integer bookID) throws DAOException;
}

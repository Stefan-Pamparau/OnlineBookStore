package com.iquestgroup.service;

import com.iquestgroup.model.Client;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

public interface ClientService {
    List<Client> listAllClients() throws ServiceException;

    String insertClient(Client client) throws ServiceException;

    String deleteClient(Integer clientID) throws ServiceException;

    String purchaseBook(Integer clientID, Integer bookID) throws ServiceException;
}

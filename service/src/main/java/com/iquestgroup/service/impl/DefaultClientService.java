package com.iquestgroup.service.impl;

import com.iquestgroup.database.ClientDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Client;
import com.iquestgroup.service.ClientService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultClientService implements ClientService {
    @Autowired
    private ClientDao clientDAO;

    @Override
    public List<Client> listAllClients() throws ServiceException {
        try {
            return clientDAO.getAllClients();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertClient(Client client) throws ServiceException {
        try {
            return clientDAO.insertClient(client);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteClient(Integer clientID) throws ServiceException {
        try {
            return clientDAO.deleteClient(clientID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String purchaseBook(Integer clientID, Integer bookID) throws ServiceException {
        try {
            return clientDAO.purchaseBook(clientID, bookID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

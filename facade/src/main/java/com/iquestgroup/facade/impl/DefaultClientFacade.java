package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.ClientFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Client;
import com.iquestgroup.service.ClientService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultClientFacade implements ClientFacade {
    @Autowired
    private ClientService clientService;

    @Override
    public List<Client> listAllClients() throws FacadeException {
        try {
            return clientService.listAllClients();
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String insertClient(Client client) throws FacadeException {
        try {
            return clientService.insertClient(client);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteClient(Integer clientID) throws FacadeException {
        try {
            return clientService.deleteClient(clientID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String purchaseBook(Integer clientID, Integer bookID) throws FacadeException {
        try {
            return clientService.purchaseBook(clientID, bookID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }
}

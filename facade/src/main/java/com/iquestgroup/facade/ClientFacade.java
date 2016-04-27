package com.iquestgroup.facade;

import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Client;

import java.util.List;

public interface ClientFacade {
    List<Client> listAllClients() throws FacadeException;

    String insertClient(Client client) throws FacadeException;

    String deleteClient(Integer clientID) throws FacadeException;

    String purchaseBook(Integer clientID, Integer bookID) throws FacadeException;
}

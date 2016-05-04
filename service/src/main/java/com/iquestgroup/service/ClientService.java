package com.iquestgroup.service;

import com.iquestgroup.model.Client;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

/**
 * Service interface which declares all methods available to manipulate client objects.
 *
 * @author Stefan Pamparau
 */
public interface ClientService {
    /**
     * Returns all the clients from the database.
     *
     * @return - a list containing all the clients from the database, null if no clients are stored
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<Client> getAllClients() throws ServiceException;

    /**
     * Returns the client with the specified id from the database.
     *
     * @param id - id of the client to be returned
     * @return - a list containing all the clients from the database, null if client is found
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    Client getClientById(Integer id) throws ServiceException;

    /**
     * Inserts a client into the database.
     *
     * @param client - client to be inserted into the database
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String insertClient(Client client) throws ServiceException;

    /**
     * Deletes a client from the database.
     *
     * @param clientID - id of client to be deleted
     * @return - a message about the operation result
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String deleteClient(Integer clientID) throws ServiceException;
}

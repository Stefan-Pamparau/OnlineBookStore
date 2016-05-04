package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Client;

import java.util.List;

/**
 * Interface which declares available methods to manipulate Client object instances
 *
 * @author Stefan Pamparau
 */
public interface ClientDao {
    /**
     * Returns all the clients from the database.
     *
     * @return - a list containing all the clients from the database, null if no clients are stored
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    List<Client> getAllClients() throws DaoException;

    /**
     * Returns the client with the specified id from the database.
     *
     * @param id - id of the client to be returned
     * @return - a list containing all the clients from the database, null if client is found
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    Client getClientById(Integer id) throws DaoException;

    /**
     * Inserts a client into the database.
     *
     * @param client - client to be inserted into the database
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String insertClient(Client client) throws DaoException;

    /**
     * Deletes a client from the database.
     *
     * @param clientID - id of client to be deleted
     * @return - a message about the operation result
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String deleteClient(Integer clientID) throws DaoException;
}

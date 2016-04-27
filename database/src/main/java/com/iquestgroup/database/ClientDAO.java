package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Client;

import java.util.List;

/**
 * Interface which declares available methods to manipulate Client object instances
 *
 * @author Stefan Pamparau
 */
public interface ClientDAO {
    /**
     * Returns all the clients from the database.
     *
     * @return - a list containing all the clients from the database, null if no clients are stored
     */
    List<Client> listAllClients() throws DAOException;

    /**
     * Inserts a client into the database.
     *
     * @param client - client to be inserted into the database
     * @return - a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String insertClient(Client client) throws DAOException;

    /**
     * Deletes a client from the database.
     *
     * @param clientID - id of client to be deleted
     * @return - a message about the operation result
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String deleteClient(Integer clientID) throws DAOException;

    String purchaseBook(Integer clientID, Integer bookID) throws DAOException;
}

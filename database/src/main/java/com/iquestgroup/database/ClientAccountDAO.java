package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.ClientAccount;

import java.util.List;

/**
 * Interface which declares all methods which manipulate ClientAccount model classes.
 *
 * @author Stefan Pamparau
 */
public interface ClientAccountDao {

    /**
     * List all client accounts stored in the database.
     *
     * @return - a list containing all client accounts, null if no accounts exist
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    List<ClientAccount> getAllClientAccounts() throws DaoException;

    /**
     * List all accounts of a specified client.
     *
     * @param clientId - id of client whose accounts will be listed
     * @return - a list containing all the account of a specified client, null if no accounts are
     * found
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    List<ClientAccount> getClientAccounts(Integer clientId) throws DaoException;

    /**
     * Retrieves an instance of a ClientAccount object with the specified id from the database.
     *
     * @param clientAccountId - the id of the client account to be retrieved
     * @return - an instance of client account which matches the given id, null if no match found
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    ClientAccount getClientAccountById(Integer clientAccountId) throws DaoException;

    /**
     * Inserts a client account into the database.
     *
     * @param account - account to be inserted
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when an Hibernate exception occurs
     */
    String insertClientAccount(ClientAccount account) throws DaoException;

    /**
     * Updates a client account based on a ClientAccount object.
     *
     * @param account - account to be updated
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    String updateClientAccount(ClientAccount account) throws DaoException;

    /**
     * Deletes a client account from the database.
     *
     * @param clientAccountId - id of the client account to be deleted
     * @return - a message about the operations result
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    String deleteClientAccount(Integer clientAccountId) throws DaoException;

    /**
     * Purchases a book by id.
     *
     * @param clientAccountID - id of the client account which will be used for purchasing
     * @param bookID          - id of the book to be purchased
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    String purchaseBook(Integer clientAccountID, Integer bookID) throws DaoException;
}

package com.iquestgroup.service;

import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * Interface which declares all methods available to manipulate Client account objects.
 *
 * @author Stefan Pamparau
 */
public interface ClientAccountService {
    /**
     * List all client accounts stored in the database.
     *
     * @return - a list containing all client accounts, null if no accounts exist
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    List<ClientAccount> getAllClientAccounts() throws ServiceException;

    /**
     * List all accounts of a specified client.
     *
     * @param clientId - id of client whose accounts will be listed
     * @return - a list containing all the account of a specified client, null if no accounts are
     * found
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    Set<ClientAccount> getClientAccounts(Integer clientId) throws ServiceException;

    /**
     * Retrieves an instance of a ClientAccount object with the specified id from the database.
     *
     * @param clientAccountId - the id of the client account to be retrieved
     * @return - an instance of client account which matches the given id, null if no match found
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    ClientAccount getClientAccountById(Integer clientAccountId) throws ServiceException;

    /**
     * Inserts a client account into the database.
     *
     * @param account - account to be inserted
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when an DaoException exception occurs
     */
    String insertClientAccount(ClientAccount account) throws ServiceException;

    /**
     * Updates a client account based on a ClientAccount object.
     *
     * @param account - account to be updated
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    String updateClientAccount(ClientAccount account) throws ServiceException;

    /**
     * Deletes a client account from the database.
     *
     * @param clientAccountId - id of the client account to be deleted
     * @return - a message about the operations result
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    String deleteClientAccount(Integer clientAccountId) throws ServiceException;

    /**
     * Purchases a book by id.
     *
     * @param clientAccountID - id of the client account which will be used for purchasing
     * @param bookID          - id of the book to be purchased
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    String purchaseBook(Integer clientAccountID, Integer bookID) throws ServiceException;
}

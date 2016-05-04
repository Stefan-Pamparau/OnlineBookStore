package com.iquestgroup.service;

import com.iquestgroup.model.Purchase;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;
import java.util.Set;

/**
 * Service interface which declares methods to manipulate purchase entities.
 *
 * @author Stefan Pamparau
 */
public interface PurchaseService {
    /**
     * Retrieves all the purchases from the database.
     *
     * @return - a list containing all the purchases stored in the database, null if no purchases
     * are found.
     * @throws ServiceException - thrown when a Hibernate exception occurs
     */
    List<Purchase> getAllPurchases() throws ServiceException;

    /**
     * Retrieves a purchase by a specifed id.
     *
     * @param id - id of the purchase to be retrieved
     * @return - a purchase with the specified id, null if no purchase found.
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    Purchase getPurchaseById(Integer id) throws ServiceException;

    /**
     * Retrieves all purchases for a specified client account id.
     *
     * @param clientAccountId - the id of the client account
     * @return - a Set containing the purchases of a client account, null if no purchases found
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    Set<Purchase> getPurchasesByClientAccountId(Integer clientAccountId) throws ServiceException;

    /**
     * Inserts a purchase into the database.
     *
     * @param purchase - purchase to be inserted
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    String insertPurchase(Purchase purchase) throws ServiceException;

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

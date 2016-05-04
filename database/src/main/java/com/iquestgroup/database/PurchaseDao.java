package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Purchase;

import java.util.List;
import java.util.Set;

/**
 * Dao interface declaring methods to manipulate purchase entities.
 *
 * @author Stefan Pamparau
 */
public interface PurchaseDao {
    /**
     * Retrieves all the purchases from the database.
     *
     * @return - a list containing all the purchases stored in the database, null if no purchases
     * are found.
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    List<Purchase> getAllPurchases() throws DaoException;

    /**
     * Retrieves a purchase by a specifed id.
     *
     * @param id - id of the purchase to be retrieved
     * @return - a purchase with the specified id, null if no purchase found.
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    Purchase getPurchaseById(Integer id) throws DaoException;

    /**
     * Retrieves all purchases for a specified client account id.
     *
     * @param clientAccountId - the id of the client account
     * @return - a Set containing the purchases of a client account, null if no purchases found
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    Set<Purchase> getPurchasesByClientAccountId(Integer clientAccountId) throws DaoException;

    /**
     * Inserts a purchase into the database.
     *
     * @param purchase - purchase to be inserted
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    String insertPurchase(Purchase purchase) throws DaoException;
}

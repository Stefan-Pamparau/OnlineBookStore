package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.UserAccount;

/**
 * Dao interface which declares methods used to manipulate User entities.
 *
 * @author Stefan Pamparau
 */
public interface UserAccountDao {

    /**
     * Returns an user account with a specified email and password if it exists, null otherwise.
     *
     * @param email    - email of the account
     * @param password - password of the account
     * @return - an user account with a specified email and password if it exists, null otherwise.
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    UserAccount getUserAccountByEmailAndPassword(String email, String password) throws DaoException;
}

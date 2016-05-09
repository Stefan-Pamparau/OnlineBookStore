package com.iquestgroup.service;

import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.exceptionHandling.ServiceException;

/**
 * Interface which declares methods to manipulate user account entities.
 *
 * @author Stefan Pamparau
 */
public interface UserAccountService {

    /**
     * Returns an user account with a specified email and password if it exists, null otherwise.
     *
     * @param email    - email of the account
     * @param password - password of the account
     * @return - an user account with a specified email and password if it exists, null otherwise.
     * @throws ServiceException - thrown when a Dao exception occurs
     */
    UserAccount getUserAccountByEmailAndPassword(String email, String password) throws ServiceException;
}

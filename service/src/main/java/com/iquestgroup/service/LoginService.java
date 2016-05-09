package com.iquestgroup.service;

import com.iquestgroup.model.User;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.exceptionHandling.ServiceException;

/**
 * Interface declaring methods used to log in into the application.
 *
 * @author Stefan Pamparau
 */
public interface LoginService {

    /**
     * Checks if an account with the specified email and password exist in the database.
     *
     * @param email - email of the account
     * @param password - password of the account
     * @return - user account with the specified email and password
     */
    UserAccount login(String email, String password) throws ServiceException;
}

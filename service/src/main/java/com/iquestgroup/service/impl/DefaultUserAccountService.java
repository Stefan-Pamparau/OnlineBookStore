package com.iquestgroup.service.impl;

import com.iquestgroup.database.UserAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.UserAccountService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Default implementation of the user account service.
 *
 * @author Stefan Pamparau
 */
public class DefaultUserAccountService implements UserAccountService {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public UserAccount getUserAccountByEmailAndPassword(String email, String password) throws ServiceException {
        try {
            return userAccountDao.getUserAccountByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getLocalizedMessage(), e);
        }
    }
}
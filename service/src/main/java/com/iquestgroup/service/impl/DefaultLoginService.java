package com.iquestgroup.service.impl;

import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.LoginService;
import com.iquestgroup.service.UserAccountService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Default implementation of the LoginService interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultLoginService implements LoginService {

    private static final Logger logger = Logger.getLogger(DefaultLoginService.class);

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public UserAccount login(String email, String password) throws ServiceException {
        try {
            logger.debug("Delegating to user account service");
            return userAccountService.getUserAccountByEmailAndPassword(email, password);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

package com.iquestgroup.service.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Default implementation of the ClientAccountService interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultClientAccountService implements ClientAccountService {

    private static final Logger logger = Logger.getLogger(DefaultClientAccountService.class);

    @Autowired
    private ClientAccountDao clientAccountDao;

    @Override
    public List<ClientAccount> getAllClientAccounts() throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.getAllClientAccounts();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<UserAccount> getClientAccounts(Integer clientId) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.getClientAccounts(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ClientAccount getClientAccountById(Integer clientAccountId) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.getClientAccountById(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ClientAccount getInitializedClientAccountById(Integer clientAccountId) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.getInitializedClientAccountById(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertClientAccount(ClientAccount account) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.insertClientAccount(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String updateClientAccount(ClientAccount account) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.updateClientAccount(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteClientAccount(Integer clientAccountId) throws ServiceException {
        try {
            logger.debug("Delegating to client account dao");
            return clientAccountDao.deleteClientAccount(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String addBalance(Integer clientAccountId, Integer balance) throws ServiceException {
        try {
            logger.debug("Retrieving client account by id");
            ClientAccount userAccount = clientAccountDao.getClientAccountById(clientAccountId);

            if (balance > 0) {
                logger.debug("Updating client account");
                userAccount.setBalance(userAccount.getBalance() + balance);
                clientAccountDao.updateClientAccount(userAccount);
            } else {
                logger.debug("Input balance is negative");
                return "Balance cannot be negative or equal to zero";
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return "Balance successfully added";
    }
}

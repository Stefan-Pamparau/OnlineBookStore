package com.iquestgroup.service.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.UserAccount;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Default implementation of the ClientAccountService interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultClientAccountService implements ClientAccountService {
    @Autowired
    private ClientAccountDao clientAccountDao;

    @Override
    public List<ClientAccount> getAllClientAccounts() throws ServiceException {
        try {
            return clientAccountDao.getAllClientAccounts();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<UserAccount> getClientAccounts(Integer clientId) throws ServiceException {
        try {
            return clientAccountDao.getClientAccounts(clientId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ClientAccount getClientAccountById(Integer clientAccountId) throws ServiceException {
        try {
            return clientAccountDao.getClientAccountById(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public ClientAccount getInitializedClientAccountById(Integer clientAccountId) throws ServiceException {
        try {
            return clientAccountDao.getInitializedClientAccountById(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertClientAccount(ClientAccount account) throws ServiceException {
        try {
            return clientAccountDao.insertClientAccount(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String updateClientAccount(ClientAccount account) throws ServiceException {
        try {
            return clientAccountDao.updateClientAccount(account);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteClientAccount(Integer clientAccountId) throws ServiceException {
        try {
            return clientAccountDao.deleteClientAccount(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String addBalance(Integer clientAccountId, Integer balance) throws ServiceException {
        try {
            ClientAccount userAccount = clientAccountDao.getClientAccountById(clientAccountId);

            if (balance > 0) {
                userAccount.setBalance(userAccount.getBalance() + balance);
                clientAccountDao.updateClientAccount(userAccount);
            } else {
                return "Balance cannot be negative or equal to zero";
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return "Balance successfully added";
    }
}

package com.iquestgroup.service.impl;

import com.iquestgroup.database.ClientAccountDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Book;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Default implementation of the ClientAccountService interface.
 *
 * @author Stefan Pamparau
 */
public class DefaultClientAccountService implements ClientAccountService {
    @Autowired
    private ClientAccountDao clientAccountDao;
    @Autowired
    private BookService bookService;

    @Override
    public List<ClientAccount> getAllClientAccounts() throws ServiceException {
        try {
            return clientAccountDao.getAllClientAccounts();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<ClientAccount> getClientAccounts(Integer clientId) throws ServiceException {
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
    public String purchaseBook(Integer clientAccountID, Integer bookID) throws ServiceException {
        try {
            Book book = bookService.getBookById(bookID);
            ClientAccount account = clientAccountDao.getClientAccountById(clientAccountID);

            if (book != null && account != null) {
                if (book.getInStock() <= 0) {
                    return "Not enough books in store";
                }

                if (account.getBalance().compareTo(book.getPrice()) < 0) {
                    return "Not enough balance in account";
                }

                book.setInStock(book.getInStock() - 1);
                account.getBooks().add(book);

                clientAccountDao.updateClientAccount(account);
                bookService.updateBook(book);
            } else {
                return "Book or account does not exist in the database.";
            }

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return "Book successfully purchased.";
    }
}

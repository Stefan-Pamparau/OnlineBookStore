package com.iquestgroup.service.impl;

import com.iquestgroup.database.PurchaseDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Book;
import com.iquestgroup.model.ClientAccount;
import com.iquestgroup.model.Purchase;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.ClientAccountService;
import com.iquestgroup.service.PurchaseService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

/**
 * Default implementation of the PurchaseService service.
 *
 * @author Stefan Pamparau
 */
public class DefaultPurchaseService implements PurchaseService {

    private static Logger logger = Logger.getLogger(DefaultPurchaseService.class);

    @Autowired
    private PurchaseDao purchaseDao;
    @Autowired
    private BookService bookService;
    @Autowired
    private ClientAccountService clientAccountService;

    @Override
    public List<Purchase> getAllPurchases() throws ServiceException {
        try {
            logger.debug("Delegating to purchase dao");
            return purchaseDao.getAllPurchases();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Purchase getPurchaseById(Integer id) throws ServiceException {
        try {
            logger.debug("Delegating to purchase dao");
            return purchaseDao.getPurchaseById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Set<Purchase> getPurchasesByClientAccountId(Integer clientAccountId) throws ServiceException {
        try {
            logger.debug("Delegating to purchase dao");
            return purchaseDao.getPurchasesByClientAccountId(clientAccountId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertPurchase(Purchase purchase) throws ServiceException {
        try {
            logger.debug("Delegating to purchase dao");
            return purchaseDao.insertPurchase(purchase);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String purchaseBook(Integer clientAccountID, Integer bookId) throws ServiceException {
        logger.debug("Retrieving book with id: " + bookId + " from the database");
        Book book = bookService.getBookById(bookId);

        logger.debug("Retrieving account with id: " + clientAccountID + " from the database");
        ClientAccount account = clientAccountService.getInitializedClientAccountById(clientAccountID);
        try {
            if (book != null && account != null) {
                logger.debug("Both book and account exist in the database");

                if (book.getInStock() <= 0) {
                    logger.debug("Not enough books in stock");
                    return "Not enough books in store";
                }

                if (account.getBalance().compareTo(book.getPrice()) < 0) {
                    logger.debug("Not enough balance in account");
                    return "Not enough balance in account";
                }

                if (checkIfBookAlreadyPurchased(clientAccountID, bookId)) {
                    logger.debug("Book already purchased by client");
                    return "Book already purchased by client";
                }


                logger.debug("Making purchasing steps");
                book.setInStock(book.getInStock() - 1);
                account.getBooks().add(book);

                Purchase purchase = new Purchase();
                purchase.setBook(book);
                purchase.setClientAccount(account);

                clientAccountService.updateClientAccount(account);
                bookService.updateBook(book);
                purchaseDao.insertPurchase(purchase);
            } else {
                return "Book or account does not exist in the database.";
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return "Book successfully purchased.";
    }

    private boolean checkIfBookAlreadyPurchased(Integer clientAccountID, Integer bookId) throws ServiceException {
        List<Purchase> purchases = getAllPurchases();
        if (purchases != null) {
            for (Purchase purchase : getAllPurchases()) {
                if (purchase.getBook().getId().equals(bookId) && purchase.getClientAccount().getId().equals(clientAccountID)) {
                    return true;
                }
            }
        }
        return false;
    }
}

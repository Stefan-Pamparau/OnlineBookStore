package com.iquestgroup.service.impl;

import com.iquestgroup.service.PurchaseService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

/**
 * Default implementation of the PurchaseService service.
 *
 * @author Stefan Pamparau
 */
public class DefaultPurchaseService implements PurchaseService {
    @Override
    public String purchaseBook(Integer clientAccountID, Integer bookID) throws ServiceException {
//        try {
//            Book book = bookService.getBookById(bookID);
//            ClientAccount account = clientAccountDao.getClientAccountById(clientAccountID);
//
//            if (book != null && account != null) {
//                if (book.getInStock() <= 0) {
//                    return "Not enough books in store";
//                }
//
//                if (account.getBalance().compareTo(book.getPrice()) < 0) {
//                    return "Not enough balance in account";
//                }
//
//                book.setInStock(book.getInStock() - 1);
//                account.getBooks().add(book);
//
//                clientAccountDao.updateClientAccount(account);
//                bookService.updateBook(book);
//            } else {
//                return "Book or account does not exist in the database.";
//            }
//
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }

        return "Book successfully purchased.";
    }
}

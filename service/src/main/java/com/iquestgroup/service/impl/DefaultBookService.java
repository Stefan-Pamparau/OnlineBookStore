package com.iquestgroup.service.impl;

import com.iquestgroup.database.BookDAO;
import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultBookService implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            return bookDAO.getAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertBook(Book book) throws ServiceException {
        try {
            return bookDAO.insertBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteBook(Integer bookID) throws ServiceException {
        try {
            return bookDAO.deleteBook(bookID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String updateBook(Book book) throws ServiceException {
        try {
            return bookDAO.updateBook(book);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Author getBookAuthor(Integer bookID) throws ServiceException {
        try {
            return bookDAO.getBookAuthor(bookID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

package com.iquestgroup.service.impl;

import com.iquestgroup.database.BookDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultBookService implements BookService {

    private static Logger logger = Logger.getLogger(DefaultBookService.class);

    @Autowired
    private BookDao bookDAO;

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.getAllBooks();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Book> getAllBooksByTitlePattern(String pattern) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.getAllBooksByTitlePattern(pattern);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Book getBookById(Integer bookId) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.getBookById(bookId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Book> getBooksOfAuthor(Author author) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.getBooksOfAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertBook(Book book) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.insertBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteBook(Integer bookID) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.deleteBook(bookID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String updateBook(Book book) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.updateBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Author getBookAuthor(Integer bookID) throws ServiceException {
        try {
            logger.debug("Deleting to book dao");
            return bookDAO.getBookAuthor(bookID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

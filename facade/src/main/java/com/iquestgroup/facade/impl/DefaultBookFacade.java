package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.BookFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.BookService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultBookFacade implements BookFacade {
    @Autowired
    private BookService bookService;

    @Override
    public List<Book> getAllBooks() throws FacadeException {
        try {
            return bookService.getAllBooks();
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String insertBook(Book book) throws FacadeException {
        try {
            return bookService.insertBook(book);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteBook(Integer bookID) throws FacadeException {
        try {
            return bookService.deleteBook(bookID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String updateBook(Book book) throws FacadeException {
        try {
            return bookService.updateBook(book);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public Author getBookAuthor(Integer bookID) throws FacadeException {
        try {
            return bookService.getBookAuthor(bookID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }
}

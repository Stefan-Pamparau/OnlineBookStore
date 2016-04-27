package com.iquestgroup.facade;

import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

public interface BookFacade {
    List<Book> getAllBooks() throws FacadeException;

    String insertBook(Book book) throws FacadeException;

    String deleteBook(Integer bookID) throws FacadeException;

    String updateBook(Book book) throws FacadeException;

    Author getBookAuthor(Integer bookID) throws FacadeException;
}

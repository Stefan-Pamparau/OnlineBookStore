package com.iquestgroup.service;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks() throws ServiceException;

    String insertBook(Book book) throws ServiceException;

    String deleteBook(Integer bookID) throws ServiceException;

    String updateBook(Book book) throws ServiceException;

    Author getBookAuthor(Integer bookID) throws ServiceException;
}

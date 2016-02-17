package com.iquestgroup.service;

import com.iquestgroup.model.Author;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors() throws ServiceException;

    Author getAuthorByID(Integer authorID) throws ServiceException;

    String insertAuthor(Author author) throws ServiceException;

    String deleteAuthor(Integer authorID) throws ServiceException;
}

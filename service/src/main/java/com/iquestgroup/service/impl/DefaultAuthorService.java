package com.iquestgroup.service.impl;

import com.iquestgroup.database.AuthorDAO;
import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultAuthorService implements AuthorService {
    @Autowired
    private AuthorDAO authorDAO;

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        try {
            return authorDAO.getAllAuthors();
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Author getAuthorByID(Integer authorID) throws ServiceException {
        try {
            return authorDAO.getAuthorByID(authorID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertAuthor(Author author) throws ServiceException {
        try {
            return authorDAO.insertAuthor(author);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteAuthor(Integer authorID) throws ServiceException {
        try {
            return authorDAO.deleteAuthor(authorID);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

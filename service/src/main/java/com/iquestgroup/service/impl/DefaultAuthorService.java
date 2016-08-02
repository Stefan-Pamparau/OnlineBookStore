package com.iquestgroup.service.impl;

import com.iquestgroup.database.AuthorDao;
import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultAuthorService implements AuthorService {

    private static final Logger logger = Logger.getLogger(DefaultAuthorService.class);

    @Autowired
    private AuthorDao authorDAO;

    @Override
    public List<Author> getAllAuthors() throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.getAllAuthors();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Author getAuthorByID(Integer authorID) throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.getAuthorByID(authorID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Author> getAuthorByNamePattern(String pattern) throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.getAuthorByNamePattern(pattern);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String insertAuthor(Author author) throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.insertAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String updateAuthor(Author author) throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.updateAuthor(author);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteAuthor(Integer authorID) throws ServiceException {
        try {
            logger.debug("Delegating to author dao");
            return authorDAO.deleteAuthor(authorID);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}

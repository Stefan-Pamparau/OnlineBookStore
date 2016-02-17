package com.iquestgroup.facade.impl;

import com.iquestgroup.facade.AuthorFacade;
import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;
import com.iquestgroup.service.AuthorService;
import com.iquestgroup.service.exceptionHandling.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultAuthorFacade implements AuthorFacade {
    @Autowired
    private AuthorService authorService;

    @Override
    public List<Author> getAllAuthors() throws FacadeException {
        try {
            return authorService.getAllAuthors();
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public Author getAuthorByID(Integer authorID) throws FacadeException {
        try {
            return authorService.getAuthorByID(authorID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String insertAuthor(Author author) throws FacadeException {
        try {
            return authorService.insertAuthor(author);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    @Override
    public String deleteAuthor(Integer authorID) throws FacadeException {
        try {
            return authorService.deleteAuthor(authorID);
        } catch (ServiceException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }
}

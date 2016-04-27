package com.iquestgroup.facade;

import com.iquestgroup.facade.exceptionHandling.FacadeException;
import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorFacade {
    List<Author> getAllAuthors() throws FacadeException;

    Author getAuthorByID(Integer authorID) throws FacadeException;

    String insertAuthor(Author author) throws FacadeException;

    String deleteAuthor(Integer authorID) throws FacadeException;
}

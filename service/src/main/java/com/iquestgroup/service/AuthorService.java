package com.iquestgroup.service;

import com.iquestgroup.model.Author;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

/**
 * Service interface which declares all operations which can be made on Authors.
 *
 * @author Stefan Pamparau
 */
public interface AuthorService {
    /**
     * Returns all authors from the database;
     *
     * @return - returns a list containing all the authors from the database, null if no authors are
     * stored
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<Author> getAllAuthors() throws ServiceException;

    /**
     * Returns an author from the database based on the author's Id.
     *
     * @param authorID - author's id based on which the author will be found
     * @return - returns an author with the specified id, null if no author found
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    Author getAuthorByID(Integer authorID) throws ServiceException;

    /**
     * Returns authors whose names match a given pattern
     *
     * @param pattern - pattern to be matched
     * @return - returns a list of authors whose name's mtch the given pattern
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<Author> getAuthorByNamePattern(String pattern) throws ServiceException;

    /**
     * Inserts an author into the database.
     *
     * @param author - author to be inserted
     * @return - returns a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String insertAuthor(Author author) throws ServiceException;

    /**
     * Updates an author based on an Author object.
     *
     * @param author - author to be updated
     * @return - returns a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String updateAuthor(Author author) throws ServiceException;

    /**
     * Deletes an author from the database.
     *
     * @param authorID - id of the author to be deleted
     * @return - returns a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String deleteAuthor(Integer authorID) throws ServiceException;
}

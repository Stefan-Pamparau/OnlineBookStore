package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;

import java.util.List;

/**
 * Interface which declares all methods available to manipulate instances of Author objects
 *
 * @author Stefan Pamparau
 */
public interface AuthorDAO {
    /**
     * Returns all authors from the database;
     *
     * @return - returns a list containing all the authors from the database, null if no authors are
     * stored
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    List<Author> getAllAuthors() throws DAOException;

    /**
     * Returns an author from the database based on the author's Id.
     *
     * @param authorID - author's id based on which the author will be found
     * @return - returns an author with the specified id, null if no author found
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    Author getAuthorByID(Integer authorID) throws DAOException;

    /**
     * Returns authors whose names match a given pattern
     *
     * @param pattern - pattern to be matched
     * @return - returns a list of authors whose name's mtch the given pattern
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    List<Author> getAuthorByNamePattern(String pattern) throws DAOException;

    /**
     * Inserts an author into the database.
     *
     * @param author - author to be inserted
     * @return - returns a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String insertAuthor(Author author) throws DAOException;

    /**
     * Updates an author based on an Author object.
     *
     * @param author - author to be updated
     * @return - returns a message about the result of the operation
     */
    String updateAuthor(Author author) throws DAOException;

    /**
     * Deletes an author from the database.
     *
     * @param authorID - id of the author to be deleted
     * @return - returns a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String deleteAuthor(Integer authorID) throws DAOException;
}

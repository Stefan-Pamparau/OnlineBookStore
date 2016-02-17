package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> getAllAuthors() throws DAOException;

    Author getAuthorByID(Integer authorID) throws DAOException;

    String insertAuthor(Author author) throws DAOException;

    String deleteAuthor(Integer authorID) throws DAOException;
}

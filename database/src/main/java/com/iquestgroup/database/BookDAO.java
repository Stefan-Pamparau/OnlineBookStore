package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

/**
 * Interface which declares all methods available to manipulate instances of book objects.
 *
 * @author Stefan Pamparau
 */
public interface BookDAO {
    /**
     * Returns all the books from the database.
     *
     * @return - a list containing all the books from the database, null if no books are present.
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    List<Book> getAllBooks() throws DAOException;

    /**
     * Returns all the books whose title's match a given pattern.
     *
     * @return - a list of books whose title match a given pattern
     */
    List<Book> getAllBooksByTitlePattern() throws DAOException;

    /**
     * Inserts a book into the database.
     *
     * @param book - book to be inserted
     * @return - a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs+
     */
    String insertBook(Book book) throws DAOException;

    /**
     * Updates a book from the database.
     *
     * @param book - book to be updated.
     * @return - a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String updateBook(Book book) throws DAOException;

    /**
     * Deletes a book from the database.
     *
     * @param bookID - the id of the book to be deleted
     * @return - a message about the result of the operation
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    String deleteBook(Integer bookID) throws DAOException;

    /**
     * Returns a book's author.
     *
     * @param bookID - the id of the book whose author will be retrieved
     * @return - the Author of the book
     * @throws DAOException - thrown when an Hibernate specific exception occurs
     */
    Author getBookAuthor(Integer bookID) throws DAOException;
}

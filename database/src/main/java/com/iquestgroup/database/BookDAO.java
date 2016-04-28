package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DaoException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

/**
 * Interface which declares all methods available to manipulate instances of book objects.
 *
 * @author Stefan Pamparau
 */
public interface BookDao {
    /**
     * Returns all the books from the database.
     *
     * @return - a list containing all the books from the database, null if no books are present.
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    List<Book> getAllBooks() throws DaoException;

    /**
     * Returns all the books whose title's match a given pattern.
     *
     * @param pattern - pattern which will be used the books title
     * @return - a list of books whose title match a given pattern
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    List<Book> getAllBooksByTitlePattern(String pattern) throws DaoException;

    /**
     * Returns all books of an author.
     *
     * @param author - author whose books will be retrieved
     * @return - a list containing all the books of an author
     * @throws DaoException - thrown when a Hibernate exception occurs
     */
    List<Book> getBooksOfAuthor(Author author) throws DaoException;

    /**
     * Inserts a book into the database.
     *
     * @param book - book to be inserted
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate specific exception occurs+
     */
    String insertBook(Book book) throws DaoException;

    /**
     * Updates a book from the database.
     *
     * @param book - book to be updated.
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String updateBook(Book book) throws DaoException;

    /**
     * Deletes a book from the database.
     *
     * @param bookID - the id of the book to be deleted
     * @return - a message about the result of the operation
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    String deleteBook(Integer bookID) throws DaoException;

    /**
     * Returns a book's author.
     *
     * @param bookID - the id of the book whose author will be retrieved
     * @return - the Author of the book
     * @throws DaoException - thrown when a Hibernate specific exception occurs
     */
    Author getBookAuthor(Integer bookID) throws DaoException;
}

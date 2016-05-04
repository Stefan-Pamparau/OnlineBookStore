package com.iquestgroup.service;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.exceptionHandling.ServiceException;

import java.util.List;

/**
 * Interface which declares all the methods available to manipulate books.
 *
 * @author Stefan Pamparau
 */
public interface BookService {
    /**
     * Returns all the books from the database.
     *
     * @return - a list containing all the books from the database, null if no books are present.
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<Book> getAllBooks() throws ServiceException;

    /**
     * Returns all the books whose title's match a given pattern.
     *
     * @param pattern - pattern which will be used the books title
     * @return - a list of books whose title match a given pattern
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    List<Book> getAllBooksByTitlePattern(String pattern) throws ServiceException;

    /**
     * Returns an instance of book from the database, based on the books id.
     *
     * @param bookId - id of book to be found
     * @return - an instance of book based on it's id. null if no instances are found
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    Book getBookById(Integer bookId) throws ServiceException;

    /**
     * Returns all books of an author.
     *
     * @param author - author whose books will be retrieved
     * @return - a list containing all the books of an author
     * @throws ServiceException - thrown when a DaoException exception occurs
     */
    List<Book> getBooksOfAuthor(Author author) throws ServiceException;

    /**
     * Inserts a book into the database.
     *
     * @param book - book to be inserted
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs+
     */
    String insertBook(Book book) throws ServiceException;

    /**
     * Updates a book from the database.
     *
     * @param book - book to be updated.
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String updateBook(Book book) throws ServiceException;

    /**
     * Deletes a book from the database.
     *
     * @param bookID - the id of the book to be deleted
     * @return - a message about the result of the operation
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    String deleteBook(Integer bookID) throws ServiceException;

    /**
     * Returns a book's author.
     *
     * @param bookID - the id of the book whose author will be retrieved
     * @return - the Author of the book
     * @throws ServiceException - thrown when a DaoException specific exception occurs
     */
    Author getBookAuthor(Integer bookID) throws ServiceException;
}

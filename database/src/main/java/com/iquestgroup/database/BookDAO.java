package com.iquestgroup.database;

import com.iquestgroup.database.exceptionHandling.DAOException;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks() throws DAOException;

    String insertBook(Book book) throws DAOException;

    String deleteBook(Integer bookID) throws DAOException;

    String updateBook(Book book) throws DAOException;

    Author getBookAuthor(Integer bookID) throws DAOException;
}

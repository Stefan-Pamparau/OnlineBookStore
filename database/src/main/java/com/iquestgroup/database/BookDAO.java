package com.iquestgroup.database;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();

    void insertBook(Book book);

    void deleteBook(Integer bookID);

    void updateBook(Book book);

    Author getBookAuthor(Integer bookID);
}

package com.iquestgroup.database;

import java.awt.print.Book;
import java.util.List;

public interface BookDAO {
    List<Book> getAllBooks();

    void insertBook(Book book);

    void deleteBook(Book book);

    void updateBook(Book book);

    void getBookAuthor(Book book);
}

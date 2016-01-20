package com.iquestgroup.facade;

import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

public interface BookFacade {
    List<Book> getAllBooks();

    void insertBook(Book book);

    void deleteBook(Integer bookID);

    void updateBook(Book book);

    Author getBookAuthor(Integer bookID);
}

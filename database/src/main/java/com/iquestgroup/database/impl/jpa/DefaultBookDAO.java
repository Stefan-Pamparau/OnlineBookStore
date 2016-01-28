package com.iquestgroup.database.impl.jpa;

import com.iquestgroup.database.BookDAO;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;

import java.util.List;

public class DefaultBookDAO implements BookDAO {
    @Override
    public List<Book> getAllBooks() {
        return null;
    }

    @Override
    public void insertBook(Book book) {

    }

    @Override
    public void deleteBook(Integer bookID) {

    }

    @Override
    public void updateBook(Book book) {

    }

    @Override
    public Author getBookAuthor(Integer bookID) {
        return null;
    }
}

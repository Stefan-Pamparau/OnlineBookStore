package com.iquestgroup.service.impl;

import com.iquestgroup.database.BookDAO;
import com.iquestgroup.model.Author;
import com.iquestgroup.model.Book;
import com.iquestgroup.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultBookService implements BookService {
    @Autowired
    private BookDAO bookDAO;

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public void insertBook(Book book) {
        bookDAO.insertBook(book);
    }

    @Override
    public void deleteBook(Integer bookID) {
        bookDAO.deleteBook(bookID);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public Author getBookAuthor(Integer bookID) {
        return bookDAO.getBookAuthor(bookID);
    }
}
